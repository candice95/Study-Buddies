package com.example.cardgame.cardgame;

import android.support.test.rule.ActivityTestRule;

import com.example.cardgame.cardgame.ui.activity.MainPageActivity;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by hacker on 12/4/15.
 */
public class MainPageActivityTest1 extends TestCase {
    @Rule
    public ActivityTestRule<MainPageActivity> activityRule = new ActivityTestRule(MainPageActivity.class);
    @Test
    //Given the user has logged in and they are at the appointment page
    public void initialState(){
        onView(withId(R.id.title)).check(matches(withText("Reserved Events")));
        onView(withId(R.id.app_name)).check(matches(withText("Study Buddies")));
        onView(withId(R.id.create_appt)).check(matches(allOf(isEnabled(), isClickable(), isDisplayed())));
        onView(withId(R.id.create_appt)).check(matches(allOf(isEnabled(), isClickable(), isDisplayed())));

    }

    //when he/she clicked the logout button, then a dialog will show up to ask user to confirm
    @Test
    public void checkLogout() {

        // toggle logout button
        onView(withId(R.id.logout)).perform(click());

        // new state - logout prompt
        onView(withText("Are you sure you want to log out?")).check(matches(isDisplayed()));

    }

}