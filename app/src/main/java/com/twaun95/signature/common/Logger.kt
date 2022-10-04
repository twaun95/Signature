package com.twaun95.signature.common

import android.util.Log
import timber.log.Timber

object Logger {

    private const val TAG = "TAEWAUN"

    fun d(message: Any) {
        Log.d(TAG, "$message")
    }
}