package com.droidrank.tictactoe

import com.droidrank.tictactoe.base.BasePresenter
import com.droidrank.tictactoe.domain.Board

interface MainContract {

    interface Presenter : BasePresenter<View> {
        fun onBlockClick(x: Int, y: Int)
        fun onRestartClick()
        fun restart()
    }

    interface View {
        val player1Symbol: String
        val player2Symbol: String
        fun render(board: Board)
        fun showRestartDialog()
    }

}
