package com.droidrank.tictactoe;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class RestartDialogBuilder {

    private RestartDialogBuilder() {
        // Empty constructor
    }

    public static AlertDialog build(Context context, final RestartConfirmationListener listener) {
        return new AlertDialog.Builder(context)
                .setTitle(R.string.app_name)
                .setMessage(R.string.restart_message)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) listener.onRestartConfirmation();
                    }
                })
                .create();
    }

    public interface RestartConfirmationListener {
        void onRestartConfirmation();
    }
}
