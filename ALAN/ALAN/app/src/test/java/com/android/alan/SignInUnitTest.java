package com.android.alan;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.concurrent.Executor;

public class SignInUnitTest {

    //
    private static FirebaseAuth firebaseAuth;
    private String email;
    private String password;

    public static void main(String[] args) {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @BeforeClass
    public static void signInBeforeClass() {
        //
    }

    @Before
    public void signInBefore() {
        //
        email = "android@developer.com";
        password = "developer,_android";
    }

    @Test(expected = ExceptionInInitializerError.class)
    public void signInTest() {
        //
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener((Executor) SignInUnitTest.this, task -> {
            //
            if (task.isSuccessful()) {
                //
                Assert.assertEquals(true, task.isSuccessful());
            } else {
                //
                Assert.assertEquals(false, !task.isSuccessful());
            }
        });
    }

    @After
    public void signInAfter() {
        //
        //firebaseAuth.getCurrentUser();
    }

    @AfterClass
    public static void signInAfterClass() {
        //
    }
}
