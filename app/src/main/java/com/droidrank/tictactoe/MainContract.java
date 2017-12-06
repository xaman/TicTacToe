package com.droidrank.tictactoe;

import com.droidrank.tictactoe.base.BasePresenter;
import com.droidrank.tictactoe.domain.Board;

public interface MainContract {
    interface Presenter extends BasePresenter<View> {
        void onBlockClick(int x, int y);
        void onRestartClick();
        void restart();
    }

    interface View {
        String getPlayer1Symbol();
        String getPlayer2Symbol();
        void render(Board board);
        void showRestartDialog();
    }
}
