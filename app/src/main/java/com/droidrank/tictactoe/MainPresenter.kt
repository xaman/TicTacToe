package com.droidrank.tictactoe

import com.droidrank.tictactoe.domain.Board
import com.droidrank.tictactoe.domain.BoardController
import kotlin.properties.Delegates

class MainPresenter : MainContract.Presenter {

    override var view: MainContract.View? = null
    private lateinit var boardController: BoardController
    private var player1Symbol by Delegates.notNull<String>()
    private var player2Symbol by Delegates.notNull<String>()

    override fun initialize() {
        view?.let {
            player1Symbol = it.player1Symbol
            player2Symbol = it.player2Symbol
            boardController = BoardController(Board(), player1Symbol, player2Symbol)
            it.render(boardController.board)
        }
    }

    override fun onBlockClick(x: Int, y: Int) {
        boardController.selectBlock(x, y)
        view?.render(boardController.board)
    }

    override fun onRestartClick() {
        when {
            boardController.board.isPlayer1Victory -> restart()
            boardController.board.isPlayer2Victory -> restart()
            boardController.board.isDraw -> restart()
            else -> view?.showRestartDialog()
        }
    }

    override fun restart() {
        boardController.reset()
        view?.render(boardController.board)
    }

    override fun onDestroy() {
        this.view = null
    }

}
