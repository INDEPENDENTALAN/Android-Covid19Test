package com.android.alan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyActivity extends AppCompatActivity {

    //
    private FirebaseAuth firebaseAuth;
    private String verificationId;

    //
    EditText editText_verify_phone, editText_verify_code;
    Button button_send, button_verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        //
        editText_verify_phone = findViewById(R.id.editText_verify_phone);
        button_send = findViewById(R.id.button_send);
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "972" + editText_verify_phone.getText().toString().trim(),
                        60,
                        TimeUnit.SECONDS,
                        (Activity) TaskExecutors.MAIN_THREAD,
                        mCallbacks);
            }
        });
        editText_verify_code = findViewById(R.id.editText_verify_code);
        button_verify = findViewById(R.id.button_verify);
        button_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                verifyVerificationCode(editText_verify_code.getText().toString().trim());
            }
        });
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            //
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            //
        }

        @Override
        public void onCodeSent(String _verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            //
            super.onCodeSent(verificationId, forceResendingToken);
            verificationId = _verificationId;
        }
    };

    private void verifyVerificationCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(VerifyActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //
                    getSharedPreferences("PHONE", MODE_PRIVATE).edit().putString("phone", editText_verify_phone.getText().toString().trim()).commit();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    //
                }
            }
        });
    }
}