package com.geekbrains.tests.presenter.details

import com.geekbrains.tests.view.ViewContract
import com.geekbrains.tests.view.details.ViewDetailsContract

internal class DetailsPresenter internal constructor(
    private var count: Int = 0
) : PresenterDetailsContract {
    override val view: ViewDetailsContract
        get() = checkNotNull(_view as? ViewDetailsContract) { "Error create ViewDetailsContract" }
    private var _view: ViewContract? = null

    override fun setCounter(count: Int) {
        this.count = count
    }

    override fun onIncrement() {
        count++
        view.setCount(count)
    }

    override fun onDecrement() {
        count--
        view.setCount(count)
    }

    override fun attach(view: ViewContract) {
        _view = view
    }

    override fun detach(view: ViewContract) {
        _view = null
    }
}
