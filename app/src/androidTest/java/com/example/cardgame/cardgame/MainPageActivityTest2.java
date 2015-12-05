package com.example.cardgame.cardgame;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.cardgame.cardgame.ui.activity.MainPageActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)

/**
 * Created by hacker on 12/4/15.
 */
public class MainPageActivityTest2 {
    @Rule
    public ActivityTestRule<MainPageActivity> activityRule = new ActivityTestRule(MainPageActivity.class);
    @Test
    //Given the user has logged in and they are at the appointment page
    //attention: because we are given user has loggin in, you need to login inorder to test
    //username: tester1  password: 123456
    public void initialState(){
        onView(withId(R.id.title)).check(matches(withText("Reserved Events")));
        onView(withId(R.id.app_name)).check(matches(withText("Study Buddies")));
        onView(withId(R.id.create_appt)).check(matches(allOf(isEnabled(), isClickable(), isDisplayed())));
        onView(withId(R.id.create_appt)).check(matches(allOf(isEnabled(), isClickable(), isDisplayed())));

    }

    //when he/she clicked the plus button, then a creating appointment page will show asking for user input
    @Test
    public void checkCreateAppointment() {

        // toggle logout button
        onView(withId(R.id.create_appt)).perform(click());

        // new state - the new create appointment page
        onView(withId(R.id.title)).check(matches(withHint("Course title")));
        onView(withId(R.id.detail)).check(matches(withHint("Detail")));
        onView(withId(R.id.creator)).check(matches(withHint("Creator")));
        onView(withId(R.id.location)).check(matches(withHint("Location")));
        onView(withId(R.id.capacity)).check(matches(withHint("Capacity")));
        onView(withId(R.id.phone)).check(matches(withHint("Phone")));
        onView(withId(R.id.email)).check(matches(withHint("Email")));
        onView(withId(R.id.submit)).check(matches(withText("Create an appointment")));

    }

}