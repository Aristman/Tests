package com.geekbrains.tests.presenter

import com.geekbrains.tests.view.ViewContract

internal interface PresenterContract {

    val view: ViewContract

    fun attach(view: ViewContract)
    fun detach(view: ViewContract)
}
