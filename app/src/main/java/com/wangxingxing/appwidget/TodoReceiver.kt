package com.wangxingxing.appwidget

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class TodoReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "TodoReceiver onReceive")
    }
}