package com.spec.knowyourspec;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.spec.knowyourspec.answer.AnswersView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityTest {

    Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

    @Before
    public void launchMainActivity(){
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void clickSettings(){
        onView(withContentDescription(R.string.settings)).perform(click());
        pressBack();
        openActionBarOverflowOrOptionsMenu(context);
        onView(withText("All Specs")).perform(click());
        pressBack();
        onView(withId(R.id.spec_image_view)).perform(click());
        /*checking if the parent view that contains a particular child is displayed*/
        onView(allOf(withId(R.id.answers_view), withChild(withText("FIRSTNAME01")))).check(matches(isDisplayed()));

        /*checking if the view itself is displayed --> this would allow direct clicking on the child view*/
        onView(allOf(withParent(withId(R.id.answers_view)), withText("FIRSTNAME01"))).check(matches(isDisplayed()));
    }

}