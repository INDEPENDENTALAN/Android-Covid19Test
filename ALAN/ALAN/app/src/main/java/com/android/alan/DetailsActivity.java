package com.android.alan;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DetailsActivity extends AppCompatActivity {

    //
    ImageButton imageButton_details_back;
    TextView textView_details_title, textView_details_details;
    ImageView imageView_details_image;
    RequestQueue requestQueue;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //
        init();
        requestQueue = Volley.newRequestQueue(DetailsActivity.this);
        //
        imageButton_details_back = findViewById(R.id.imageButton_details_back);
        imageButton_details_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                finish();
            }
        });
        textView_details_title = findViewById(R.id.textView_details_title);
        textView_details_title.setText(getIntent().getStringExtra("Title"));
        imageView_details_image = findViewById(R.id.imageView_details_image);
        ImageRequest imageRequest = new ImageRequest(getIntent().getStringExtra("Image"), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(final Bitmap response) {
                //
                imageView_details_image.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //
            }
        });
        requestQueue.add(imageRequest);
        textView_details_details = findViewById(R.id.textView_details_details);
        textView_details_details.setText(getIntent().getStringExtra("Details"));
    }

    private void init() {
    }
}
