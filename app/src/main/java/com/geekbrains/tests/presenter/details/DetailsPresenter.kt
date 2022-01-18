package com.geekbrains.tests.presenter.details

import com.geekbrains.tests.view.ViewContract
import com.geekbrains.tests.view.details.ViewDetailsContract

internal class DetailsPresenter internal constructor(
    private var count: Int = 0
) : PresenterDetailsContract {
    override var view: ViewDetailsContract? = null

    override fun setCounter(count: Int) {
        this.count = count
    }

    override fun onIncrement() {
        count++
        view?.setCount(count)
    }

    override fun onDecrement() {
        count--
        view?.setCount(count)
    }

    override fun attach(view: ViewContract) {
        this.view = view as ViewDetailsContract
    }

    override fun detach(view: ViewContract) {
        this.view = null
    }
}
