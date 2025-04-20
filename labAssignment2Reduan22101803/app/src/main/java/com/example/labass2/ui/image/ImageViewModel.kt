package com.example.labass2.ui.image

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Pinch to zoom the image"
    }
    val text: LiveData<String> = _text
}