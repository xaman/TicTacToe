package com.droidrank.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.droidrank.tictactoe.domain.Board;
import com.droidrank.tictactoe.domain.BoardStatus;

public class MainActivity extends AppCompatActivity implements MainContract.View, RestartDialogBuilder.RestartConfirmationListener {

    private String TAG = MainActivity.class.getSimpleName();
    private Button block1, block2, block3, block4, block5, block6, block7, block8, block9, restart;
    private MainContract.Presenter presenter;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        setListeners();
        initializePresenter();
    }

    private void initializeViews() {
        block1 = (Button) findViewById(R.id.bt_block1);
        block2 = (Button) findViewById(R.id.bt_block2);
        block3 = (Button) findViewById(R.id.bt_block3);
        block4 = (Button) findViewById(R.id.bt_block4);
        block5 = (Button) findViewById(R.id.bt_block5);
        block6 = (Button) findViewById(R.id.bt_block6);
        block7 = (Button) findViewById(R.id.bt_block7);
        block8 = (Button) findViewById(R.id.bt_block8);
        block9 = (Button) findViewById(R.id.bt_block9);
        result = (TextView) findViewById(R.id.tv_show_result);
        restart = (Button) findViewById(R.id.bt_restart_game);
    }

    private void setListeners() {
        restart.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Log.d(TAG, "Click on restart!");
                presenter.onRestartClick();
            }
        });
        setSquareClickListener(block1, 0, 0);
        setSquareClickListener(block2, 0, 1);
        setSquareClickListener(block3, 0, 2);
        setSquareClickListener(block4, 1, 0);
        setSquareClickListener(block5, 1, 1);
        setSquareClickListener(block6, 1, 2);
        setSquareClickListener(block7, 2, 0);
        setSquareClickListener(block8, 2, 1);
        setSquareClickListener(block9, 2, 2);
    }

    private void setSquareClickListener(View blockView, final int x, final int y) {
        blockView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Log.d(TAG, "Click on block: " + x + ", " + y);
                presenter.onBlockClick(x, y);
            }
        });
    }

    private void initializePresenter() {
        presenter = new MainPresenter();
        presenter.setView(this);
        presenter.initialize();
    }

    @Override public void render(Board board) {
        Log.d(TAG, board.toString());
        renderBlocks(board.getBlocks());
        renderStatus(board.getStatus());
        renderButton(board);
    }

    private void renderBlocks(String[][] blocks) {
        block1.setText(blocks[0][0]);
        block2.setText(blocks[0][1]);
        block3.setText(blocks[0][2]);
        block4.setText(blocks[1][0]);
        block5.setText(blocks[1][1]);
        block6.setText(blocks[1][2]);
        block7.setText(blocks[2][0]);
        block8.setText(blocks[2][1]);
        block9.setText(blocks[2][2]);
    }

    private void renderStatus(BoardStatus status) {
        int textRes = R.string.empty;
        if (status == BoardStatus.PLAYER_1_WINS) {
            textRes = R.string.player_1_wins;
        } else if (status == BoardStatus.PLAYER_2_WINS) {
            textRes = R.string.player_2_wins;
        } else if (status == BoardStatus.DRAW) {
            textRes = R.string.draw;
        }
        result.setText(textRes);
    }

    private void renderButton(Board board) {
        restart.setText(board.isEmpty() ? R.string.restart_button_text_initially : R.string.restart_button_text_in_middle_of_game);
    }

    @Override public void showRestartDialog() {
        RestartDialogBuilder.build(this, this).show();
    }

    @Override public void onRestartConfirmation() { presenter.restart(); }

    @Override public String getPlayer1Symbol() { return getString(R.string.player_1_move); }

    @Override public String getPlayer2Symbol() { return getString(R.string.player_2_move); }

}
