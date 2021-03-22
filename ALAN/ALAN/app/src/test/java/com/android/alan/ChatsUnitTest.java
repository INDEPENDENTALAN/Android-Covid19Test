package com.android.alan;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChatsUnitTest {

    //
    private DatabaseReference databaseReference;
    boolean task;

    @BeforeClass
    public static void chatsBeforeClass() {
        //
    }

    @Before
    public void chatsBefore() {
        //
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Test
    public void chatsTest() throws Exception {
        //
        databaseReference.child("Chats").push().setValue(new ChatsEntity("android@developer.com", "Hi,"));
        databaseReference.child("Chats").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //
                task = true;
                Assert.assertEquals(true, task);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //
                task = false;
                Assert.assertEquals(false, task);
            }
        });
        //
    }

    @After
    public void chatsAfter() {
        //
    }

    @AfterClass
    public static void chatsAfterClass() {
        //
    }
}
