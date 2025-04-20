package com.example.vangtichai

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var amount = 0
    private lateinit var tvTaka: TextView
    private lateinit var noteViews: Map<Int, TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTaka = findViewById(R.id.textViewTaka)

        noteViews = mapOf(
            500 to findViewById(R.id.fiveH),
            100 to findViewById(R.id.oneH),
            50 to findViewById(R.id.fifty),
            20 to findViewById(R.id.twenty),
            10 to findViewById(R.id.ten),
            5 to findViewById(R.id.five),
            2 to findViewById(R.id.two),
            1 to findViewById(R.id.one)
        )

        setupButtons()
    }

    @SuppressLint("SetTextI18n")
    private fun setupButtons() {
        val numberButtons = listOf(
            R.id.button0 to 0, R.id.button1 to 1, R.id.button2 to 2,
            R.id.button3 to 3, R.id.button4 to 4, R.id.button5 to 5,
            R.id.button6 to 6, R.id.button7 to 7, R.id.button8 to 8, R.id.button9 to 9
        )

        numberButtons.forEach { (id, value) ->
            findViewById<Button>(id).setOnClickListener { appendNumber(value) }
        }

        findViewById<Button>(R.id.buttonClear).setOnClickListener { clearInput() }
    }

    @SuppressLint("SetTextI18n")
    private fun appendNumber(value: Int) {
        if (amount < 1_000_000) { // Prevent input exceeding 999,999
            amount = amount * 10 + value
            tvTaka.text = "Taka: $amount"
            calculateChange()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun clearInput() {
        amount = 0
        tvTaka.text = "Taka: 0"
        resetNotes()
    }

    @SuppressLint("SetTextI18n")
    private fun calculateChange() {
        var remaining = amount

        for ((note, textView) in noteViews) {
            val count = remaining / note
            textView.text = "$note : $count"
            remaining %= note
        }
    }

    @SuppressLint("SetTextI18n")
    private fun resetNotes() {
        for ((note, textView) in noteViews) {
            textView.text = "$note : 0"
        }
    }
}
