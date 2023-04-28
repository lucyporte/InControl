package com.example.incontrol;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import junit.framework.TestCase;
import junit.framework.TestCase;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
import static java.util.regex.Pattern.matches;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.*;
import androidx.navigation.*;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.*;

import androidx.test.espresso.*;

public class SeventhFragmentTest extends TestCase {

    //new rule
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch() {
        View view = mainActivity.findViewById(R.id.helloTV);
        assertNotNull(view);   //we will use this a lot
    }


    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }

    @Test
    public void testButtonToSecondActivity() {
        assertNotNull(mainActivity.findViewById(R.id.toSecondBT));

        //we can use espresso in here
        onView(withId(R.id.toSecondBT)).perform(click());

        //add a monitor for second activity
        Instrumentation.ActivityMonitor monitor = getInstrumentation()
                .addMonitor(SecondActivity.class.getName(), null, false);

    }
        @Test
        public void testButtonToSecondActivity () {
	    ...

            //wait for 5 seconds
            Activity secondActivity = getInstrumentation()
                    .waitForMonitorWithTimeout(monitor, 5000);
            assertNotNull(secondActivity);

        }

        public void testOnCreateView () {
        }

        public void testOnViewCreated () {
        }
    }