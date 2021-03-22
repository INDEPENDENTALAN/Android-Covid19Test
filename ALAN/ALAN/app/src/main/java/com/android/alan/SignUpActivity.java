package com.android.alan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    //
    EditText editTextSignUpName;
    EditText editText_sign_up_password;
    EditText editText_sign_up_confirm;
    Button button_sign_up;
    Button button_sign_up_sign_in;

    //
    private FirebaseAuth firebaseAuth;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //
        firebaseAuth = FirebaseAuth.getInstance();
        //
        editTextSignUpName = findViewById(R.id.editText_sign_up_name);
        editText_sign_up_password = findViewById(R.id.editText_sign_up_password);
        editText_sign_up_confirm = findViewById(R.id.editText_sign_up_confirm);
        button_sign_up = findViewById(R.id.button_sign_up);
        button_sign_up.setOnClickListener(view -> {
            //
            firebaseAuth.createUserWithEmailAndPassword(
                    editTextSignUpName.getText().toString(),
                    editText_sign_up_password.getText().toString())
                    .addOnCompleteListener(SignUpActivity.this,
                            task -> {
                                //
                                if (task.isSuccessful()) {
                                    //
                                    getSharedPreferences("NAME", MODE_PRIVATE).edit()
                                            .putString("name",
                                                    editTextSignUpName.getText().toString().trim())
                                            .apply();
                                    startActivityForResult(new Intent(SignUpActivity.this, VerifyActivity.class), 0);
                                } else {
                                    //
                                }
                            });
        });
        button_sign_up_sign_in = findViewById(R.id.button_sign_up_sign_in);
        button_sign_up_sign_in.setOnClickListener(view -> {
            //
            setResult(RESULT_CANCELED);
            finish();
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
            }
        }
    }
}
