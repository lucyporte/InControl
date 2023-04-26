package com.example.incontrol;

import junit.framework.TestCase;

import static org.junit.Assert.*;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import org.junit.*;

@RunWith(JUnit4.class)
public class FirstFragmentTest extends TestCase {

    @Test
    fun testNavigationToInGameScreen() {
        // Create a mock NavController
        val mockNavController = mock(NavController::class.java)

        // Create a graphical FragmentScenario for the TitleScreen
        val titleScenario = launchFragmentInContainer<FirstFragment>()

        // Set the NavController property on the fragment
        titleScenario.onFragment { fragment ->
                Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        // Verify that performing a click prompts the correct Navigation action
        onView(ViewMatchers.withId(R.id.button_first)).perform(ViewActions.click())
        verify(mockNavController).navigate(R.id.action_FirstFragment_to_FifthFragment)
    }





    @Test fun navigationTest(){
        val scenario = launchFragmentInContainer<FirstFragment>()
        onView(withId(R.id.textview_first)).perform(click());
    }

        @Test
        fun launchFragmentAndVerifyUI() {
            // use launchInContainer to launch the fragment with UI
            launchFragmentInContainer<FirstFragment>()
            // now use espresso to look for the fragment's text view and verify it is displayed
            onView(withId(R.id.textview_first)).check(matches(withText("I am a fragment")));
        }

    @Test
    public void testNav() {

        // Create a TestNavHostController
        TestNavHostController navController = new TestNavHostController(
                ApplicationProvider.getApplicationContext());

        // Create a graphical FragmentScenario for the TitleScreen
        FragmentScenario<TitleScreen> titleScenario = FragmentScenario.launchInContainer(TitleScreen.class);

        titleScenario.onFragment(fragment ->
                // Set the graph on the TestNavHostController
                navController.setGraph(R.navigation.trivia);

        // Make the NavController available via the findNavController() APIs
        Navigation.setViewNavController(fragment.requireView(), navController)
        );

        // Verify that performing a click changes the NavController’s state
        onView(ViewMatchers.withId(R.id.play_btn)).perform(ViewActions.click());
        assertThat(navController.currentDestination.id).isEqualTo(R.id.in_game);
    }
}