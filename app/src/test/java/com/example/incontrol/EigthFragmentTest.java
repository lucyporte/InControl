package com.example.incontrol;

import junit.framework.TestCase;
import android.content.Intent;
import android.os.IBinder;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.Navigation;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.*;

@RunWith(JUnit4.class)
public class EigthFragmentTest extends TestCase {

    @Test testEventFragment() {
        // The "fragmentArgs" argument is optional.
        fragmentArgs = bundleOf(“selectedListItem” to 0)
        scenario = launchFragmentInContainer<EventFragment>(fragmentArgs)
        ...
    }

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void listGoesOverTheFold() {
        onView(withText("Hello world!")).check(matches(isDisplayed()));
    }

    public void testOnCreateView() {
    }

    public void testOnViewCreated() {
    }
}