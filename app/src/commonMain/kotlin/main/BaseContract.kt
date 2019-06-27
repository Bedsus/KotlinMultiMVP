package main

interface BaseView {
    fun showError(error: String)
    fun showLoading()
    fun hideLoading()
}

interface BasePresenter {
    fun detachView()
}