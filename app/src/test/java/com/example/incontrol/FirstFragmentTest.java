package com.example.incontrol;

import junit.framework.TestCase;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
import static java.util.regex.Pattern.matches;

import android.content.Intent;
import android.os.IBinder;

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

@RunWith(JUnit4.class)
public class FirstFragmentTest extends TestCase {

    public class HelloWorldEspressoTest {
        @Rule
        public ActivityScenarioRule<MainActivity> activityRule =
                new ActivityScenarioRule<>(MainActivity.class);

        @Test
        public void listGoesOverTheFold() {
            onView(withText("Hello world!")).check(matches(isDisplayed()));
        }

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







        @Test
        fun testNavigationToInGameScreen() {
            // Create a mock NavController
            val mockNavController = mock(NavController:: class.java);

            // Create a graphical FragmentScenario for the TitleScreen
            val titleScenario = launchFragmentInContainer < FirstFragment > ()

            // Set the NavController property on the fragment
            titleScenario.onFragment {
                fragment ->
                        Navigation.setViewNavController(fragment.requireView(), mockNavController)
            }

            // Verify that performing a click prompts the correct Navigation action
            onView(ViewMatchers.withId(R.id.button_first)).perform(ViewActions.click())
            verify(mockNavController).navigate(R.id.action_FirstFragment_to_FifthFragment)
        }


        @Test
        fun navigationTest() {
            val scenario = launchFragmentInContainer < FirstFragment > ()
            onView(withId(R.id.textview_first)).perform(click());
        }

        @Test
        fun launchFragmentAndVerifyUI() {
            // use launchInContainer to launch the fragment with UI
            launchFragmentInContainer<FirstFragment> ()
            // now use espresso to look for the fragment's text view and verify it is displayed
            onView(withId(R.id.textview_first)).check(matches(withText("I am a fragment")));
        }


        @Test
        public void testNav() {

            // Create a TestNavHostController
            TestNavHostController navController = new TestNavHostController(
                    ApplicationProvider.getApplicationContext());

            // Create a graphical FragmentScenario for the TitleScreen
            FragmentScenario<com.example.FirstFragment> titleScenario = FragmentScenario.launchInContainer(com.example.FirstFragment.class);

            titleScenario.onFragment(fragment ->
                    // Set the graph on the TestNavHostController
                    navController.setGraph(R.id.FirstFragment);

            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(requireView(), navController)
        );

            // Verify that performing a click changes the NavController’s state
            onView(ViewMatchers.withId(R.id.button_first)).perform(ViewActions.click());
            assertThat(navController.currentDestination.id).isEqualTo(R.id.fifthFragment);
        }
    }
}