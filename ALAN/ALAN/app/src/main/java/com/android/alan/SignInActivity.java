package com.android.alan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    //
    EditText editText_sign_in_name, editText_sign_in_password;
    Button button_sign_in, button_sign_in_sign_up;

    //
    private FirebaseAuth firebaseAuth;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        //
        firebaseAuth = FirebaseAuth.getInstance();
        //
        editText_sign_in_name = findViewById(R.id.editText_sign_in_name);
        editText_sign_in_password = findViewById(R.id.editText_sign_in_password);
        button_sign_in = findViewById(R.id.button_sign_in);
        button_sign_in.setOnClickListener(view -> {
            //
            firebaseAuth.signInWithEmailAndPassword(editText_sign_in_name.getText().toString(), editText_sign_in_password.getText().toString()).addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //
                    if (task.isSuccessful()) {
                        //
                        getSharedPreferences("NAME", MODE_PRIVATE).edit().putString("name", editText_sign_in_name.getText().toString().trim()).commit();
                        finish();
                    } else {
                        //
                    }
                }
            });
        });
        button_sign_in_sign_up = findViewById(R.id.button_sign_in_sign_up);
        button_sign_in_sign_up.setOnClickListener(view -> {
            //
            startActivityForResult(new Intent(SignInActivity.this, SignUpActivity.class), 0);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //
        if (requestCode == 0) {
            //
            if (resultCode == RESULT_OK) {
                //
                finish();
            } else if (resultCode == RESULT_CANCELED) {
                //
            }
        }
    }
}
