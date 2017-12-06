package com.droidrank.tictactoe.domain;

public class BoardController {

    private Board board;
    private String player1Symbol;
    private String player2Symbol;

    public BoardController(Board board, String player1Symbol, String player2Symbol) {
        this.board = board;
        this.player1Symbol = player1Symbol;
        this.player2Symbol = player2Symbol;
    }

    public Board getBoard() { return board; }

    public void reset() {
        board.reset();
    }

    public void selectBlock(int x, int y) {
        if (board.isPlayer1Turn() && board.isEmpty(x, y)) {
            board.setPosition(x, y, player1Symbol);
            board.setStatus(BoardStatus.PLAYER_2_MOVES);
        } else if (board.isPlayer2Turn() && board.isEmpty(x, y)) {
            board.setPosition(x, y, player2Symbol);
            board.setStatus(BoardStatus.PLAYER_1_MOVES);
        }
        checkFull();
        checkLine();
    }

    private void checkFull() { if (board.isFull()) board.setStatus(BoardStatus.DRAW); }

    private void checkLine() {
        String symbol;
        String[][] blocks = board.getBlocks();
        for (int row = 0; row < Board.ROWS; row++) {
            symbol = checkRow(blocks, row);
            if (symbol != null) {
                setWinner(symbol);
                return;
            }
        }
        for (int col = 0; col < Board.COLUMNS; col++) {
            symbol = checkColumn(blocks, col);
            if (symbol != null) {
                setWinner(symbol);
                return;
            }
        }
        symbol = checkDiagonals(blocks);
        if (symbol != null) setWinner(symbol);
    }

    private String checkColumn(String[][] blocks, int rowPos) {
        String[] row = blocks[rowPos];
        String symbol = row[0];
        for (int pos = 1; pos < row.length; pos++) {
            if (row[pos] == null || !row[pos].equals(symbol)) return null;
        }
        return symbol;
    }

    private String checkRow(String[][] blocks, int columnPos) {
        String symbol = blocks[0][columnPos];
        for (int pos = 1; pos < blocks.length; pos++) {
            if (blocks[pos][columnPos] == null || !blocks[pos][columnPos].equals(symbol)) return null;
        }
        return symbol;
    }

    private String checkDiagonals(String[][] blocks) {
        String symbol = blocks[0][0];
        for (int i = 1; i < blocks.length; i++) {
            if (blocks[i][i] == null || !blocks[i][i].equals(symbol)) symbol = null;
        }
        if (symbol != null) return symbol;
        symbol = blocks[0][blocks.length - 1];
        for (int i = 1; i < blocks.length; i++) {
            if (blocks[i][blocks.length - i - 1] == null || !blocks[i][blocks.length - i - 1].equals(symbol)) symbol = null;
        }
        if (symbol != null) return symbol;
        return null;
    }

    private void setWinner(String symbol) {
        if (player1Symbol.equals(symbol)) {
            board.setStatus(BoardStatus.PLAYER_1_WINS);
        } else if (player2Symbol.equals(symbol)) {
            board.setStatus(BoardStatus.PLAYER_2_WINS);
        }
    }
}
