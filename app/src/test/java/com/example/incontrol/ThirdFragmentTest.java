package com.example.incontrol;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.core.app.ApplicationProvider;

import static com.google.android.material.testutils.NavigationViewActions.addHeaderView;
import static com.google.android.material.testutils.NavigationViewActions.inflateHeaderView;
import static com.google.android.material.testutils.NavigationViewActions.removeHeaderView;
import static com.google.android.material.testutils.NavigationViewActions.removeMenuItem;
import static com.google.android.material.testutils.NavigationViewActions.setCheckedItem;
import static com.google.android.material.testutils.NavigationViewActions.setIconForMenuItem;
import static com.google.android.material.testutils.NavigationViewActions.setItemBackground;
import static com.google.android.material.testutils.NavigationViewActions.setItemBackgroundResource;
import static com.google.android.material.testutils.NavigationViewActions.setItemIconTintList;
import static com.google.android.material.testutils.NavigationViewActions.setItemTextAppearance;
import static com.google.android.material.testutils.NavigationViewActions.setItemTextColor;

import android.content.res.Resources;

import java.util.HashMap;

@RunWith(JUnit4.class)
public class ThirdFragmentTest extends TestCase {

        @Rule
        public final ActivityTestRule<NavigationViewActivity> activityTestRule =
                new ActivityTestRule<>(NavigationViewActivity.class);

        private static final int[] MENU_CONTENT_ITEM_IDS = {
                R.id.destination_home,
                R.id.destination_profile,
                R.id.destination_people,
                R.id.destination_settings
        };

    private NavigationTestView navigationView;

    @Before
    public void setUp() throws Exception {
        final NavigationViewActivity activity = activityTestRule.getActivity();
        drawerLayout = activity.findViewById(R.id.drawer_layout);
        navigationView = drawerLayout.findViewById(R.id.start_drawer);

        // Close the drawer to reset the state for the next test
        onView(withId(R.id.drawer_layout)).perform(closeDrawer(GravityCompat.START));

        final Resources res = activity.getResources();
        menuStringContent = new HashMap<>(MENU_CONTENT_ITEM_IDS.length);
        menuStringContent.put(R.id.destination_home, res.getString(R.string.navigate_home));
        menuStringContent.put(R.id.destination_profile, res.getString(R.string.navigate_profile));
        menuStringContent.put(R.id.destination_people, res.getString(R.string.navigate_people));
        menuStringContent.put(R.id.destination_settings, res.getString(R.string.navigate_settings));
    }

    @Test
    public void testBasics() {
        // Open our drawer
        onView(withId(R.id.drawer_layout)).perform(openDrawer(GravityCompat.START));

        // Check the contents of the Menu object
        final Menu menu = navigationView.getMenu();
        assertNotNull("Menu should not be null", menu);
        assertEquals(
                "Should have matching number of items", MENU_CONTENT_ITEM_IDS.length + 2, menu.size());
        for (int i = 0; i < MENU_CONTENT_ITEM_IDS.length; i++) {
            final MenuItem currItem = menu.getItem(i);
            assertEquals("ID for Item #" + i, MENU_CONTENT_ITEM_IDS[i], currItem.getItemId());
        }

        // Check that we have the expected menu items in our NavigationView
        for (int i = 0; i < MENU_CONTENT_ITEM_IDS.length; i++) {
            onView(
                    allOf(
                            withText(menuStringContent.get(MENU_CONTENT_ITEM_IDS[i])),
                            isDescendantOfA(withId(R.id.start_drawer))))
                    .check(matches(isDisplayed()));
        }
    }


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

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


    public void testOnCreateView() {
    }

    public void testOnViewCreated() {
    }


}