package com.twaun95.signature.presentation.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    val hello by lazy { MutableLiveData("hello") }
}