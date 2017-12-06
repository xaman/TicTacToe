package com.droidrank.tictactoe.domain;

import android.text.TextUtils;

public class Board {

    public static final int ROWS = 3;
    public static final int COLUMNS = 3;

    private String[][] blocks;
    private BoardStatus status;

    public Board() {
        reset();
    }

    public boolean isEmpty(int x, int y) {
        return TextUtils.isEmpty(blocks[x][y]);
    }

    public void setPosition(int x, int y, String value) {
        blocks[x][y] = value;
    }

    public String[][] getBlocks() { return blocks.clone(); }

    public BoardStatus getStatus() { return status; }

    public void setStatus(BoardStatus status) { this.status = status; }

    public boolean isPlayer1Turn() { return status == BoardStatus.PLAYER_1_MOVES; }

    public boolean isPlayer2Turn() { return status == BoardStatus.PLAYER_2_MOVES; }

    public boolean isPlayer1Victory() { return status == BoardStatus.PLAYER_1_WINS; }

    public boolean isPlayer2Victory() { return status == BoardStatus.PLAYER_2_WINS; }

    public boolean isDraw() { return status == BoardStatus.DRAW; }

    public boolean isEmpty() {
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLUMNS; y++) {
                if (blocks[x][y] != null) return false;
            }
        }
        return true;
    }

    public boolean isFull() {
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLUMNS; y++) {
                if (blocks[x][y] == null) return false;
            }
        }
        return true;
    }

    public void reset() {
        status = BoardStatus.PLAYER_1_MOVES;
        blocks = new String[ROWS][COLUMNS];
    }

    @Override public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Status: " + status + "\n");
        builder.append("Board:\n");
        String block;
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLUMNS; y++) {
                block = blocks[x][y];
                builder.append("[" + (block == null ? " " : block) + "]");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
