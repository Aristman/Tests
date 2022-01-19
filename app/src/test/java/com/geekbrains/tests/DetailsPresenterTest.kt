package com.geekbrains.tests

import android.os.Build
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.view.details.DetailsActivity
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class DetailsPresenterTest {

    private lateinit var presenter: DetailsPresenter

    private lateinit var scenario: ActivityScenario<DetailsActivity>

    @Before
    fun setup() {
        presenter = DetailsPresenter()
        scenario = ActivityScenario.launch(DetailsActivity::class.java)
        scenario.onActivity {
            presenter.attach(it)
        }
    }

    @Test
    fun Increment_Test() {
        presenter.onIncrement()
        scenario.onActivity {
            val resultText = it.findViewById<TextView>(R.id.totalCountTextView)
            assertEquals(resultText.text, getResultString(1))
        }
    }

    @Test
    fun Decrement_Test() {
        presenter.setCounter(8)
        presenter.onDecrement()
        scenario.onActivity {
            val resultText = it.findViewById<TextView>(R.id.totalCountTextView)
            assertEquals(resultText.text, getResultString(7))
        }
    }

    @After
    fun close() {
        scenario.onActivity { presenter.detach(it) }
        scenario.close()
    }

    private fun getResultString(count: Int): String =
        String.format("Number of results: %d", count)
}
