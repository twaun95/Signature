package com.twaun95.signature.presentation.utils.dialog

import android.content.Context
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener


object ColorPickerDialog {

    fun show(context: Context, selectColorListener: (color: Int)->Unit) {
        ColorPickerDialog.Builder(context)
            .setTitle("색 선택")
            .setPreferenceName("MyColorPickerDialog")
            .setPositiveButton("적용", ColorEnvelopeListener { envelope, fromUser -> selectColorListener.invoke(envelope.color) })
            .setNegativeButton("취소") { dialogInterface, i -> dialogInterface.dismiss() }
            .attachAlphaSlideBar(true) // the default value is true.
            .attachBrightnessSlideBar(true) // the default value is true.
            .setBottomSpace(0) // set a bottom space between the last slidebar and buttons.
            .show()
    }
}