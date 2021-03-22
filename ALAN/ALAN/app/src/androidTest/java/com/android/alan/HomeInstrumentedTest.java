package com.android.alan;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeInstrumentedTest {

    @Rule
    public ActivityScenarioRule<HomeActivity> activityScenarioRule = new ActivityScenarioRule<>(HomeActivity.class);

    @Test
    public void recyclerViewTest(){
        onView(withId(R.id.recyclerView_home)).perform(RecyclerViewActions.actionOnItemAtPosition(6, click()));
    }

    @Test
    public void viewHolderTest(){
        onView(withId(R.id.recyclerView_home)).check(matches(hasItem(hasDescendant(withText("homeViewHolder")))));
    }

    public static Matcher<View> hasItem(Matcher<View> matcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {

            @Override public void describeTo(Description description) {
                description.appendText("has item: ");
                matcher.describeTo(description);
            }

            @Override protected boolean matchesSafely(RecyclerView view) {
                HomeAdapter adapter = (HomeAdapter) view.getAdapter();
                for (int position = 0; position < adapter.getItemCount(); position++) {
                    int type = adapter.getItemViewType(position);
                    HomeAdapter.HomeViewHolder homeViewHolder = adapter.createViewHolder(view, type);
                    adapter.onBindViewHolder(homeViewHolder, position);
                    if (matcher.matches(homeViewHolder.itemView)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }
}
