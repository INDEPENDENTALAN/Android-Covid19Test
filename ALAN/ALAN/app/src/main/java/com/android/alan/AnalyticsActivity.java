package com.android.alan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AnalyticsActivity extends AppCompatActivity {

    //
    ImageButton imageButton_analytics_back, imageButton_analytics_analytics;
    EditText editText_analytics_country_code;
    TextView textView_analytics_cases, textView_analytics_deaths, textView_analytics_recovered;

    //
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    String url = "https://api.covid19api.com/summary";

    //
    String cases, deaths, recovered, country_code;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);
        //
        requestQueue = Volley.newRequestQueue(AnalyticsActivity.this);
        //
        imageButton_analytics_back = findViewById(R.id.imageButton_analytics_back);
        imageButton_analytics_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                finish();
            }
        });
        editText_analytics_country_code = findViewById(R.id.editText_analytics_country_code);
        imageButton_analytics_analytics = findViewById(R.id.imageButton_analytics_analytics);
        imageButton_analytics_analytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                if (editText_analytics_country_code.getText().toString().length() == 2) {
                    //
                    country_code = editText_analytics_country_code.getText().toString().toUpperCase();
                    jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //
                            try {
                                //
                                JSONArray jsonArray = response.getJSONArray("Countries");
                                for(int i = 0; i < jsonArray.length(); i++) {
                                    //
                                    if (jsonArray.getJSONObject(i).getString("CountryCode").equals(country_code)) {
                                        //
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        cases = jsonObject.getString("TotalConfirmed");
                                        deaths = jsonObject.getString("TotalDeaths");
                                        recovered = jsonObject.getString("TotalRecovered");
                                        break;
                                    }
                                }
                            } catch (JSONException e) {
                                //
                            }
                            textView_analytics_cases.setText(cases);
                            textView_analytics_deaths.setText(deaths);
                            textView_analytics_recovered.setText(recovered);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //
                        }
                    });
                    requestQueue.add(jsonObjectRequest);
                }
            }
        });
        textView_analytics_cases = findViewById(R.id.textView_analytics_cases);
        textView_analytics_deaths = findViewById(R.id.textView_analytics_deaths);
        textView_analytics_recovered = findViewById(R.id.textView_analytics_recovered);
        //
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //
                try {
                    //
                    JSONObject jsonObject = response.getJSONObject("Global");
                    cases = jsonObject.getString("TotalConfirmed");
                    deaths = jsonObject.getString("TotalDeaths");
                    recovered = jsonObject.getString("TotalRecovered");
                } catch (JSONException e) {
                    //
                }
                textView_analytics_cases.setText(cases);
                textView_analytics_deaths.setText(deaths);
                textView_analytics_recovered.setText(recovered);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //
            }
        });
        requestQueue.add(jsonObjectRequest);
        //
    }
}
