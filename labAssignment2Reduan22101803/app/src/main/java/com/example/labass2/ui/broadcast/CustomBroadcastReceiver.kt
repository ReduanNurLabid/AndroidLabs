package com.example.labass2.ui.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class CustomBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val message = intent.getStringExtra("message")
        if (message != null) {
            Toast.makeText(context, "Received broadcast: $message", Toast.LENGTH_LONG).show()
        }
    }
}