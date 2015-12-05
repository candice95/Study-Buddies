package com.example.cardgame.cardgame;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.cardgame.cardgame.ui.activity.OnBoardingActivity;

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
public class OnBoardingActivityTest {

    @Rule
    public ActivityTestRule<OnBoardingActivity> activityRule = new ActivityTestRule(OnBoardingActivity.class);

    @Test
    public void initialUITest(){
        onView(withId(R.id.username)).check(matches(allOf(isDisplayed(), isEnabled(), withHint("Username"))));
        onView(withId(R.id.password)).check(matches(allOf(isDisplayed(), isEnabled(), withHint("Password"))));
        onView(withId(R.id.submit)).check(matches(allOf(isEnabled(), isClickable(), isDisplayed(), withText("Log In"))));
        onView(withId(R.id.options)).check(matches(isClickable()));
        onView(withId(R.id.description)).check(matches(allOf(isDisplayed(), isEnabled(), withText("Don't have an account? "))));
        onView(withId(R.id.action)).check(matches(allOf(isDisplayed(), isEnabled(), withText("Sign up."))));
    }

    @Test
    public void checkToggle() {
        // initial state
        onView(withId(R.id.submit)).check(matches(allOf(isEnabled(), isClickable(), isDisplayed(), withText("Log In"))));
        onView(withId(R.id.description)).check(matches(allOf(isDisplayed(), isEnabled(), withText("Don't have an account? "))));
        onView(withId(R.id.action)).check(matches(allOf(isDisplayed(), isEnabled(), withText("Sign up."))));

        // toggle
        onView(withId(R.id.options)).perform(click());

        // new state
        onView(withId(R.id.submit)).check(matches(allOf(isEnabled(), isClickable(), isDisplayed(), withText("Sign Up"))));
        onView(withId(R.id.description)).check(matches(allOf(isDisplayed(), isEnabled(), withText("Already have an account? "))));
        onView(withId(R.id.action)).check(matches(allOf(isDisplayed(), isEnabled(), withText("Log in."))));

        // toggle back
        onView(withId(R.id.options)).perform(click());
    }

}
