package com.android.alan;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.Executor;

@RunWith(MockitoJUnitRunner.class)
public class SignUpUnitTest {

    //
    private FirebaseAuth firebaseAuth;
    private String email;
    private String password;

    @BeforeClass
    public static void signUpBeforeClass() {
        //
    }

    @Before
    public void signUpBefore() {
        //
        email = "android@developer.com";
        password = "developer,_android";
    }

    @Test//(expected = ExceptionInInitializerError.class)
    public void signUpTest() {
        //firebaseAuth = FirebaseAuth.getInstance();
        Mockito.when(FirebaseAuth.getInstance()).thenReturn(firebaseAuth);
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((Executor) SignUpUnitTest.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //
                if (task.isSuccessful()) {
                    //
                    Mockito.doThrow(ExceptionInInitializerError.class);
                    Assert.assertEquals(true, task.isSuccessful());
                } else {
                    //
                    Mockito.doThrow(ExceptionInInitializerError.class);
                    Assert.assertEquals(false, !task.isSuccessful());
                }
            }
        });
    }

    @After
    public void signUpAfter() {
        //
        //firebaseAuth.getCurrentUser();
    }

    @AfterClass
    public static void signUpAfterClass() {
        //
    }
}
