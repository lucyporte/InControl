package com.example.incontrol;

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
public class SixthFragmentTest extends TestCase {

    //Espresso
    //Mockito

    @Rule
    public ActivityScenarioRule<MyClass> activityRule =
            new ActivityScenarioRule(MyClass.class);

    @Test
    public void myClassMethod_ReturnsTrue() { ... }

    @Rule
    public final ServiceTestRule serviceRule = new ServiceTestRule();

    @Test
    public void testWithStartedService() {
        serviceRule.startService(
                new Intent(ApplicationProvider.getApplicationContext(),
                        MyService.class));
        // Add your test code here.
    }

    @Test
    public void testWithBoundService() {
        IBinder binder = serviceRule.bindService(
                new Intent(ApplicationProvider.getApplicationContext(),
                        MyService.class));
        MyService service = ((MyService.LocalBinder) binder).getService();
        assertThat(service.doSomethingToReturnTrue()).isTrue();
    }

    private static final String stringToBeTyped = "Espresso";

    @Rule
    public ActivityTestRule<MainActivity>; activityRule =
            new ActivityTestRule<>;(MainActivity.class);

    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.editTextUserInput))
                .perform(typeText(stringToBeTyped), closeSoftKeyboard());
        onView(withId(R.id.changeTextBt)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.textToBeChanged))
                .check(matches(withText(stringToBeTyped)));
    }

    @Test
    public void greeterSaysHello() {
        onView(withId(R.id.name_field)).perform(typeText("Steve"));
        onView(withId(R.id.greet_button)).perform(click());
        onView(withText("Hello Steve!")).check(matches(isDisplayed()));
    }

}