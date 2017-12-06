package com.droidrank.tictactoe.base;

public interface BasePresenter<T> {
    void setView(T view);
    void initialize();
    void onResume();
    void onPause();
    void onDestroy();
}
