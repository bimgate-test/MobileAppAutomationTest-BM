package com.mytaxi.android_demo;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import com.mytaxi.android_demo.activities.MainActivity;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;

/**
 * Created by bratislav.miletic on 09/03/2018.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestsLogInSelect {

    String usrNameTrue = "whiteelephant261";
    String passwdTrue = "video";
    String usrNameFalse = "usrWrong";
    String passwdFalse = "passwdWrong";
    String driverFirstLetters = "sa";
    String driverNameSurname = "Sarah Friedrich";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    @Before
    public void setUp() throws Exception {
        ///Before Test case execution
    }

    @Test
    public void testALoginTrue() throws InterruptedException {
        //screen login
        onView(withId(R.id.login)).check(matches(isDisplayed()));
        //username
        onView(withId(R.id.edt_username)).perform(typeText(usrNameFalse));
        //password
        onView(withId(R.id.edt_password)).perform(typeText(passwdFalse));
        //button
        onView(withId(R.id.btn_login)).check(matches(withText("Login"))).perform(click(),closeSoftKeyboard());
        //message Login failed
        onView(allOf(withId(R.id.login), withText("Login failed")));
    }

    @Test
    public void testBLoginFalse() throws InterruptedException {
        //screen login
        onView(withId(R.id.login)).check(matches(isDisplayed()));
        //username
        onView(withId(R.id.edt_username)).perform(typeText(usrNameTrue));
        //password
        onView(withId(R.id.edt_password)).perform(typeText(passwdTrue));
        //button
        onView(withId(R.id.btn_login)).check(matches(withText("Login"))).perform(click());
        Thread.sleep(1000);
        //confirm home page
        onView(withId(R.id.fab)).check(matches(isDisplayed()));
    }

    @Test
    public void testCselectDriver() throws InterruptedException {
        //screen confirm
        onView(withId(R.id.searchContainer)).check(matches(isDisplayed()));
        Thread.sleep(1000);
        //insert text
        onView(withId(R.id.textSearch)).perform(typeText(driverFirstLetters));
        onView(allOf(withId(R.id.drawer_layout), withText(driverNameSurname)));
        onView(withId(R.id.drawer_layout)).perform(click());
        //perform call
        onView(withId(R.id.fab)).perform(click());
    }




    @After
    public void tearDown() throws Exception {
        //After Test case Execution
    }


}
