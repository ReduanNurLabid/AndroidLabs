package com.example.labass2.ui.broadcast

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.labass2.R

class BroadcastSenderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_sender)
        
        val messageEditText: EditText = findViewById(R.id.edit_message)
        val sendButton: Button = findViewById(R.id.btn_send)
        
        sendButton.setOnClickListener {
            val message = messageEditText.text.toString()
            if (message.isNotEmpty()) {
                // Start the receiver activity
                val intent = Intent(this, BroadcastReceiverActivity::class.java)
                intent.putExtra("broadcast_type", "custom")
                intent.putExtra("message", message)
                startActivity(intent)
            }
        }
    }
}