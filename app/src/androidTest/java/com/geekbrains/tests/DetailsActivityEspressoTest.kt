package com.geekbrains.tests

import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.view.details.DetailsActivity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsActivityEspressoTest {

    private lateinit var scenario: ActivityScenario<DetailsActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(DetailsActivity::class.java)
    }

    @Test
    fun activity_NotNull() {
        scenario.onActivity {
            assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResume() {
        assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activityTextView_crate() {
        scenario.onActivity {
            val totalTextView = it.findViewById<TextView>(R.id.totalCountTextView)
            assertNotNull(totalTextView)
        }
    }

    @Test
    fun activityTextView_InitText() {
        onView(withId(R.id.totalCountTextView))
            .check(matches(withText("Number of results: 0")))
    }

    @Test
    fun activityTextView_IsDisplayed() {
        onView(withId(R.id.totalCountTextView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun activityTextView_IsCompleteDisplayed() {
        onView(withId(R.id.totalCountTextView))
            .check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun activityButtons_isVisible() {
        onView(withId(R.id.incrementButton))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.decrementButton))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun activityIncrementButton_IsClicked() {
        onView(withId(R.id.incrementButton))
            .perform(click())
        onView(withId(R.id.totalCountTextView))
            .check(matches(withText("Number of results: 1")))
    }

    @Test
    fun activityDecrementButton_IsClicked() {
        onView(withId(R.id.decrementButton))
            .perform(click())
        onView(withId(R.id.totalCountTextView))
            .check(matches(withText("Number of results: -1")))
    }

    @After
    fun close() {
        scenario.close()
    }
}
