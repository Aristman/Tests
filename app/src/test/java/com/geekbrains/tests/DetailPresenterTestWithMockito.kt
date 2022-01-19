package com.geekbrains.tests

import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.view.details.ViewDetailsContract
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailPresenterTestWithMockito {
    private lateinit var presenter: DetailsPresenter

    @Mock
    private lateinit var view: ViewDetailsContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailsPresenter()
    }

    @Test
    fun Attach_Test() {
        assertNull("view is Attach", presenter.view)
        presenter.attach(view)
        assertNotNull("view is Attach", presenter.view)
    }

    @Test
    fun Detach_Test() {
        presenter.attach(view)
        assertNotNull(presenter.view)
        presenter.detach(view)
        assertNull(presenter.view)
    }

    @After
    fun close() {
        presenter.detach(view)
    }
}
