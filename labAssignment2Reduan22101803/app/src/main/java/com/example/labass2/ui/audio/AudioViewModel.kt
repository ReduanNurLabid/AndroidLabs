package com.example.labass2.ui.audio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AudioViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Audio Player"
    }
    val text: LiveData<String> = _text
}