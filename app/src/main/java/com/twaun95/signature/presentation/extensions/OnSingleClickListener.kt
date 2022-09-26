package com.twaun95.signature.presentation.extensions

import android.os.SystemClock
import android.view.View

class OnSingleClickListener(
    private val onClickListener: (view: View) -> Unit
) : View.OnClickListener{
    companion object {
        const val INTERVAL = 200
    }

    private var lastClickedTime = 0L

    override fun onClick(view: View) {
        val onClickedTime = SystemClock.elapsedRealtime()
        // 간격보다 작으면 클릭 no
        if ((onClickedTime-lastClickedTime) < INTERVAL) { return }

        lastClickedTime = onClickedTime
        onClickListener.invoke(view)
    }
}

fun View.setOnSingleClickListener(
    onClickListener: (view: View) -> Unit
) {
    setOnClickListener(OnSingleClickListener(onClickListener))
}