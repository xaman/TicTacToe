package com.droidrank.tictactoe.domain

class BoardController(
        val board: Board,
        private val player1Symbol: String,
        private val player2Symbol: String) {

    fun reset() = board.reset()

    fun selectBlock(x: Int, y: Int) {
        if (board.isPlayer1Turn && board.isEmpty(x, y)) {
            board.setPosition(x, y, player1Symbol)
            board.status = BoardStatus.PLAYER_2_MOVES
        } else if (board.isPlayer2Turn && board.isEmpty(x, y)) {
            board.setPosition(x, y, player2Symbol)
            board.status = BoardStatus.PLAYER_1_MOVES
        }
        checkFull()
        checkLine()
    }

    private fun checkFull() = with(board) {
        if (isFull) status = BoardStatus.DRAW
    }

    private fun checkLine() {
        var symbol: String?
        val blocks = board.blocks
        for (row in 0 until Board.ROWS) {
            symbol = checkRow(blocks, row)
            symbol?.let { setWinner(it) }
        }
        for (col in 0 until Board.COLUMNS) {
            symbol = checkColumns(blocks, col)
            symbol?.let { setWinner(it) }
        }
        symbol = checkDiagonals(blocks)
        symbol?.let { setWinner(it) }
    }

    private fun checkRow(blocks: Array<Array<String?>>, rowPos: Int): String? {
        val row = blocks[rowPos]
        val symbol = row.first()
        val isLine = row.all { it == symbol }
        return if (isLine) symbol else null
    }

    private fun checkColumns(blocks: Array<Array<String?>>, columnPos: Int): String? {
        val symbol = blocks[0][columnPos]
        val isLine = (1 until blocks.size).all { pos -> blocks[pos][columnPos] == symbol }
        return if (isLine) symbol else null
    }

    private fun checkDiagonals(blocks: Array<Array<String?>>): String? {
        // First diagonal
        var symbol = blocks[0][0]
        var isLine = (1 until blocks.size).all { pos -> blocks[pos][pos] == symbol }
        if (isLine) return symbol
        // Second diagonal
        symbol = blocks[0][blocks.size - 1]
        isLine = (1 until blocks.size).all { pos -> blocks[pos][blocks.size - 1 - pos] == symbol}
        return if (isLine) symbol else null
    }

    private fun setWinner(symbol: String) = when (symbol) {
        player1Symbol -> board.status = BoardStatus.PLAYER_1_WINS
        player2Symbol -> board.status = BoardStatus.PLAYER_2_WINS
        else -> Unit
    }
}
