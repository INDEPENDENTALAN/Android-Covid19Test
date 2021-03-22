package com.android.alan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class ChatsActivity extends AppCompatActivity {

    //
    private final static int REQUEST_PERMISSIONS = 0;

    //
    ImageButton imageButton_chats_back, imageButton_chats_speech, imageButton_chats_ok;
    RecyclerView recyclerView_chats;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ChatsEntity> chatsEntityArrayList;
    ChatsAdapter chatsAdapter;
    String name;
    EditText editText_chats;

    //
    private SpeechRecognizer speechRecognizer;
    public static final Integer RecordAudioRequestCode = 1;

    //
    private DatabaseReference databaseReference;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        //
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //
        name = getIntent().getStringExtra("Name");
        //
        imageButton_chats_back = findViewById(R.id.imageButton_chats_back);
        imageButton_chats_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                finish();
            }
        });
        recyclerView_chats = findViewById(R.id.recyclerView_chats);
        recyclerView_chats.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(ChatsActivity.this);
        recyclerView_chats.setLayoutManager(layoutManager);
        chatsEntityArrayList = new ArrayList<>();
        //
        databaseReference.child("Chats").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //
                for (DataSnapshot dataSnapshot_: dataSnapshot.getChildren()) {
                    //
                    chatsEntityArrayList.add(dataSnapshot_.getValue(ChatsEntity.class));
                }
                chatsAdapter = new ChatsAdapter(chatsEntityArrayList);
                recyclerView_chats.setAdapter(chatsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //
            }
        });
        //
        editText_chats = findViewById(R.id.editText_chats);
        //
        onRequestPermissions();
        //
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(ChatsActivity.this);
        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        //
        imageButton_chats_speech = findViewById(R.id.imageButton_chats_speech);
        imageButton_chats_speech.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    //
                    speechRecognizer.stopListening();
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    //
                    speechRecognizer.startListening(speechRecognizerIntent);
                }
                return false;
            }
        });
        //
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {
                //
            }

            @Override
            public void onBeginningOfSpeech() {
                //
                editText_chats.setText("");
                editText_chats.setHint("Listening...");
            }

            @Override
            public void onRmsChanged(float v) {
                //
            }

            @Override
            public void onBufferReceived(byte[] bytes) {
                //
            }

            @Override
            public void onEndOfSpeech() {
                //
            }

            @Override
            public void onError(int i) {
                //
            }

            @Override
            public void onResults(Bundle bundle) {
                //
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (data != null) {
                    editText_chats.setText(data.get(0));
                }
            }

            @Override
            public void onPartialResults(Bundle bundle) {
                //
            }

            @Override
            public void onEvent(int i, Bundle bundle) {
                //
            }
        });
        //
        imageButton_chats_ok = findViewById(R.id.imageButton_chats_ok);
        imageButton_chats_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                databaseReference.child("Chats").push().setValue(new ChatsEntity(name, editText_chats.getText().toString()));
            }
        });
    }

    private void onRequestPermissions() {
        //
        if (ContextCompat.checkSelfPermission(ChatsActivity.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            //not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(ChatsActivity.this, Manifest.permission.RECORD_AUDIO)) {
                //
            } else {
                //
                ActivityCompat.requestPermissions(ChatsActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_PERMISSIONS);
            }
        } else {
            //granted
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //
        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                //
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //granted
                } else {
                    //denied
                }
                return;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
    }
}
