package com.example.incontrol;

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
public class FourthFragmentTest extends TestCase {

    @Rule
    public ActivityScenarioRule<MyClass> activityRule =
            new ActivityScenarioRule(MyClass.class);

    @Test
    public void myClassMethod_ReturnsTrue() { ... }

    @Test
    public void testNavigationToInGameScreen() {

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

    @Test
    public void NavTest() {
        ActivityScenario<MyActivity> scenario = ActivityScenario.launch(MyActivity.class);
    }

    public void testOnCreateView() {
    }

    public void testOnViewCreated() {
    }
}