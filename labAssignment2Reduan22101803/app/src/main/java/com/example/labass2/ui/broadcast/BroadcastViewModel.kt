package com.example.labass2.ui.broadcast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BroadcastViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Select broadcast type and press proceed"
    }
    val text: LiveData<String> = _text
}