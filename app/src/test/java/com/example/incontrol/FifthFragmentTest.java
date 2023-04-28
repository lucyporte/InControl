package com.example.incontrol;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import junit.framework.TestCase;
import org.junit.Rule;
import androidx.fragment.app.testing.launchFragmentInContainer;
import androidx.test.espresso.Espresso.onView;
import androidx.test.espresso.assertion.ViewAssertions.matches;
import androidx.test.espresso.matcher.ViewMatchers.withId;
import androidx.test.espresso.matcher.ViewMatchers.withText;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.LooperMode;

public class FifthFragmentTest extends TestCase {

    public static final String STRING_TO_BE_TYPED = "Espresso";

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testOnCreateView() {
    }

    public void testOnViewCreated() {
    }

    public void testSetVal() {
    }

    public void testGetVal() {
    }

    public void testSetTher() {
    }

    public void testGetTher() {
    }
}