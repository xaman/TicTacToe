package com.droidrank.tictactoe.domain

import android.text.TextUtils

class Board {

    companion object {
        const val ROWS = 3
        const val COLUMNS = 3
    }

    lateinit var blocks: Array<Array<String?>>
    lateinit var status: BoardStatus

    init { reset() }

    val isPlayer1Turn: Boolean get() = status == BoardStatus.PLAYER_1_MOVES

    val isPlayer2Turn: Boolean get() = status == BoardStatus.PLAYER_2_MOVES

    val isPlayer1Victory: Boolean get() = status == BoardStatus.PLAYER_1_WINS

    val isPlayer2Victory: Boolean get() = status == BoardStatus.PLAYER_2_WINS

    val isDraw: Boolean get() = status == BoardStatus.DRAW

    val isEmpty: Boolean get() = blocks.all { row -> row.all { block -> block == null } }

    val isFull: Boolean get() = blocks.all { row -> row.all { block -> block != null } }

    fun isEmpty(x: Int, y: Int): Boolean {
        return TextUtils.isEmpty(blocks[x][y])
    }

    fun setPosition(x: Int, y: Int, value: String) { blocks[x][y] = value }

    fun reset() {
        status = BoardStatus.PLAYER_1_MOVES
        blocks = Array(ROWS, { arrayOfNulls<String>(COLUMNS) })
    }

    override fun toString(): String {
        var result = "Status: $status\n"
        result += "Board:\n"
        blocks.forEach { it.forEach { result += "[${it ?: " "}]" }.also { result += "\n" } }
        return result
    }
}
