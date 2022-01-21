package com.geekbrains.tests.automator

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class BehaviorTest {
    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val packageName = context.packageName

    @Before
    fun setup() {
        uiDevice.pressHome()
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)?.apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)
        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)
    }

    @Test
    fun test_MainActivityIsStarted() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        Assert.assertNotNull(editText)
    }

    @Test
    fun test_SearchIsPositive() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        val searchButton = uiDevice.findObject(By.res(packageName, "searchButton"))
        editText.text = "UiAutomator"
        searchButton.click()
//        Espresso.onView(ViewMatchers.withId(R.id.searchEditText))
//            .perform(ViewActions.pressImeActionButton())
        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )
        Assert.assertEquals(changedText.text.toString(), getResultString(697))
    }

    @Test
    fun test_OpenDetailsScreen() {
        val toDetails = uiDevice.findObject(
            By.res(
                packageName,
                "toDetailsActivityButton"
            )
        )
        toDetails.click()
        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountDetailTextView")),
                TIMEOUT
            )
        Assert.assertEquals(changedText.text, getResultString(0))
    }

    @Test
    fun test_ExecuteRequestAndOpenDetailsScreen() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        editText.text = "UiAutomator"
        val requestButton = uiDevice.findObject(By.res(packageName, "searchButton"))
        val toDetailsButton = uiDevice.findObject(By.res(packageName, "toDetailsActivityButton"))
        requestButton.click()
        val mainChangedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )
        val mainText = mainChangedText.text
        Assert.assertNotNull(mainChangedText)
        toDetailsButton.click()
        val detailsChangedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountDetailTextView")),
                TIMEOUT
            )
        Assert.assertEquals(detailsChangedText.text, mainText)
    }

    private fun getResultString(number: Int): String =
        "Number of results: $number"

    companion object {
        private const val TIMEOUT = 5000L
    }
}
