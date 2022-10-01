package com.twaun95.signature.presentation.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twaun95.signature.presentation.model.ToggledState

class MainActivityViewModel : ViewModel() {
    val toggledState by lazy { MutableLiveData(ToggledState.IDLE) }

    fun toggle(state: ToggledState) {
        toggledState.value = state
    }
}