package com.twaun95.signature.presentation.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    val isErasingMode by lazy { MutableLiveData(false) }

    fun toggleEraser() {
        isErasingMode.value = isErasingMode.value == false
    }
}