package com.android.alan;

import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(Parameterized.class)
public class HomeUnitTest {

    FirebaseFirestore firebaseFirestore;

    public HomeUnitTest(FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = firebaseFirestore;
    }

    @Parameterized.Parameters
    public static Collection<DetailsEntity> data() {
        return new ArrayList<DetailsEntity>();
    }

    @BeforeClass
    public static void homeBeforeClass() {
        //
    }

    @Before
    public void homeBefore() {
        //
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Test
    public void homeTest() {
        //
        firebaseFirestore.collection("Details").get().addOnCompleteListener(task -> {
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
    public void homeAfter() {
        //
    }

    @AfterClass
    public static void homeAfterClass() {
        //
    }
}
