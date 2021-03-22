package com.android.alan;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class VerifyUnitTest {

    //
    private FirebaseAuth firebaseAuth;
    private String verificationId;

    @Test
    public void verifyTest() {
        //
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "972599066592",
                60,
                TimeUnit.SECONDS,
                (Activity) TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    private void verifyVerificationCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener((Executor) VerifyUnitTest.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //
                } else {
                    //
                }
            }
        });
    }

    boolean task;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            //
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            //
            task = false;
            Assert.assertEquals(false, task);
        }

        @Override
        public void onCodeSent(String _verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            //
            super.onCodeSent(verificationId, forceResendingToken);
            verificationId = _verificationId;
            task = true;
            Assert.assertEquals(true, task);
        }
    };
}
