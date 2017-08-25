package com.rijo.usbankassignment.ui.mainScreen;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.rijo.usbankassignment.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppNavigationTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void appNavigationTest() {
        onView(withId(R.id.autoTextButton))
                .check(matches(isDisplayed()));
        onView(withId(R.id.autoTextButton)).perform(click());
        onView(withId(R.id.autoCompleteTextView))
                .check(matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.currencyButton))
                .check(matches(isDisplayed()));
        onView(withId(R.id.currencyButton)).perform(click());
        onView(withId(R.id.convertionView))
                .check(matches(isDisplayed()));
        pressBack();
        pressBack();
        onView(withId(R.id.audioStreamButton))
                .check(matches(isDisplayed()));
        onView(withId(R.id.audioStreamButton)).perform(click());
        onView(withId(R.id.startButton))
                .check(matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.autoTextButton))
                .check(matches(isDisplayed()));

    }

}
