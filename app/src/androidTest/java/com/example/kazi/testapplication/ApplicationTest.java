package com.example.kazi.testapplication;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;



import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


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

    @Test
    public void nav_background_image(){
       onView(withId(R.id.navigation_view_header_background_image));
     }

    @Test
    public void textTest(){
        onView(withText("UFC UNOFFICIAL"));
    }


}
