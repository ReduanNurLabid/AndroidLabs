package com.example.labass2.ui.broadcast

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.labass2.R

class BroadcastFragment : Fragment() {

    private lateinit var broadcastViewModel: BroadcastViewModel
    private var selectedBroadcastType = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        broadcastViewModel =
            ViewModelProvider(this).get(BroadcastViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_broadcast, container, false)
        
        val spinner: Spinner = root.findViewById(R.id.broadcast_spinner)
        val proceedButton: Button = root.findViewById(R.id.btn_proceed)
        
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.broadcast_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedBroadcastType = position
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedBroadcastType = 0
            }
        }
        
        proceedButton.setOnClickListener {
            when (selectedBroadcastType) {
                0 -> { // Custom broadcast receiver
                    val intent = Intent(activity, BroadcastSenderActivity::class.java)
                    startActivity(intent)
                }
                1 -> { // System battery notification
                    val intent = Intent(activity, BroadcastReceiverActivity::class.java)
                    intent.putExtra("broadcast_type", "system_battery")
                    startActivity(intent)
                }
            }
        }
        
        return root
    }
}