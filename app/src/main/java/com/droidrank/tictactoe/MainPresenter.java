package com.droidrank.tictactoe;

import com.droidrank.tictactoe.domain.Board;
import com.droidrank.tictactoe.domain.BoardController;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private BoardController boardController;
    private String player1Symbol;
    private String player2Symbol;

    public void setView(MainContract.View view) {
        this.view = view;
    }

    @Override public void initialize() {
        player1Symbol = view.getPlayer1Symbol();
        player2Symbol = view.getPlayer2Symbol();
        boardController = new BoardController(new Board(), player1Symbol, player2Symbol);
        renderBoard();
    }

    @Override public void onResume() {
        // Empty
    }

    @Override public void onPause() {
        // Empty
    }

    @Override public void onDestroy() {
        this.view = null;
    }

    @Override public void onBlockClick(int x, int y) {
        boardController.selectBlock(x, y);
        renderBoard();
    }

    @Override public void onRestartClick() {
        if (view != null) view.showRestartDialog();
    }

    @Override public void restart() {
        boardController.reset();
        renderBoard();
    }

    private void renderBoard() {
        if (view != null) view.render(boardController.getBoard());
    }
}
