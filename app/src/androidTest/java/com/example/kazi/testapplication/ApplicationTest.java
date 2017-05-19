package com.example.kazi.testapplication;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;



import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import org.junit.runner.RunWith;



/**
 * Created by Kazi on 19/May/17.
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void setup() {
    }

    @After
    public void tearDown() {
    }



}
