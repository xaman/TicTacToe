package com.droidrank.tictactoe

import android.content.Context
import android.support.v7.app.AlertDialog

object RestartDialogBuilder {

    fun build(context: Context, listener: () -> Unit): AlertDialog {
        return AlertDialog.Builder(context)
                .setTitle(R.string.app_name)
                .setMessage(R.string.restart_message)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, { _, _ -> listener() })
                .create()
    }
}
