package main

interface BaseView {
    fun showError(error: String)
}

interface BasePresenter {
    fun detachView()
}