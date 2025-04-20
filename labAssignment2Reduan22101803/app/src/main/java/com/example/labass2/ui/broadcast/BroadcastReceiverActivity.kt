package com.example.labass2.ui.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.labass2.R

class BroadcastReceiverActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var broadcastType: String? = null
    private var batteryReceiver: BroadcastReceiver? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_receiver)
        
        resultTextView = findViewById(R.id.text_result)
        
        broadcastType = intent.getStringExtra("broadcast_type")
        
        when (broadcastType) {
            "custom" -> {
                val message = intent.getStringExtra("message")
                if (message != null) {
                    resultTextView.text = getString(R.string.received_message, message)
                    // Send a broadcast with the message
                    sendCustomBroadcast(message)
                }
            }
            "system_battery" -> {
                // Register for battery updates
                registerBatteryReceiver()
            }
        }
    }
    
    private fun sendCustomBroadcast(message: String) {
        val intent = Intent("com.example.labass2.CUSTOM_ACTION")
        intent.putExtra("message", message)
        sendBroadcast(intent)
    }
    
    private fun registerBatteryReceiver() {
        batteryReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                val batteryPct = level * 100 / scale.toFloat()
                resultTextView.text = getString(R.string.battery_level, batteryPct.toInt())
            }
        }
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryReceiver, filter)
    }
    
    override fun onDestroy() {
        super.onDestroy()
        batteryReceiver?.let { unregisterReceiver(it) }
    }
}