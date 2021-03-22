package com.android.alan;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AnalyticsUnitTest {

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    String url;
    boolean task;

    @BeforeClass
    public static void analyticsBeforeClass() {
        //
    }

    @Before
    public void analyticsBefore() {
        //
        url = "https://api.covid19api.com/summary";
    }

    @Test
    public void analyticsTest() {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            //
            task = true;
            Assert.assertEquals(true, task);
        }, error -> {
            //
            task = false;
            Assert.assertEquals(true, task);
        });
        requestQueue.add(jsonObjectRequest);
    }

    @After
    public void analyticsAfter() {
        //
        requestQueue.stop();
    }

    @AfterClass
    public static void analyticsAfterClass() {
        //
    }
}
