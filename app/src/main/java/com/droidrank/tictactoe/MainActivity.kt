package com.droidrank.tictactoe

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

import com.droidrank.tictactoe.domain.Board
import com.droidrank.tictactoe.domain.BoardStatus
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity(), MainContract.View {

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }

    private lateinit var presenter: MainContract.Presenter
    override val player1Symbol: String get() = getString(R.string.player_1_move)
    override val player2Symbol: String get() = getString(R.string.player_2_move)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
        initializePresenter()
    }

    private fun setListeners() {
        restartButton.onClick{ presenter.onRestartClick() }
        block1Button.onClick { presenter.onBlockClick(0, 0) }
        block2Button.onClick { presenter.onBlockClick(0, 1) }
        block3Button.onClick { presenter.onBlockClick(0, 2) }
        block4Button.onClick { presenter.onBlockClick(1, 0) }
        block5Button.onClick { presenter.onBlockClick(1, 1) }
        block6Button.onClick { presenter.onBlockClick(1, 2) }
        block7Button.onClick { presenter.onBlockClick(2, 0) }
        block8Button.onClick { presenter.onBlockClick(2, 1) }
        block9Button.onClick { presenter.onBlockClick(2, 2) }
    }

    private fun initializePresenter() {
        presenter = MainPresenter()
        presenter.view = this
        presenter.initialize()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun render(board: Board) {
        Log.d(TAG, board.toString())
        renderBlocks(board.blocks)
        renderStatus(board.status)
        renderButton(board)
    }

    private fun renderBlocks(blocks: Array<Array<String?>>) {
        block1Button.text = blocks[0][0]
        block2Button.text = blocks[0][1]
        block3Button.text = blocks[0][2]
        block4Button.text = blocks[1][0]
        block5Button.text = blocks[1][1]
        block6Button.text = blocks[1][2]
        block7Button.text = blocks[2][0]
        block8Button.text = blocks[2][1]
        block9Button.text = blocks[2][2]
    }

    private fun renderStatus(status: BoardStatus) {
        val textRes = when(status) {
            BoardStatus.PLAYER_1_WINS -> R.string.player_1_wins
            BoardStatus.PLAYER_2_WINS -> R.string.player_2_wins
            BoardStatus.DRAW -> R.string.draw
            else -> R.string.empty
        }
        resultTextView.setText(textRes)
    }

    private fun renderButton(board: Board) {
        val textRes = when {
            board.isEmpty -> R.string.restart_button_text_initially
            else -> R.string.restart_button_text_in_middle_of_game
        }
        restartButton.setText(textRes)
    }

    override fun showRestartDialog() = RestartDialogBuilder.build(this, presenter::restart).show()

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}
