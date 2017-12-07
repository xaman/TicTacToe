package com.droidrank.tictactoe.base

interface BasePresenter<T> {
    var view: T?

    fun initialize() {
        // Default empty implementation
    }

    fun onResume() {
        // Default empty implementation
    }

    fun onPause() {
        // Default empty implementation
    }

    fun onDestroy() {
        // Default empty implementation
    }

}
