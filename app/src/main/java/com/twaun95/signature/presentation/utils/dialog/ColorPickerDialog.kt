package com.twaun95.signature.presentation.utils.dialog

import android.content.Context
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.ColorPickerView
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import com.skydoves.colorpickerview.preference.ColorPickerPreferenceManager
import com.twaun95.signature.R


object ColorPickerDialog {

    fun show(context: Context, title: String, dismissListener: ()->Unit,  selectColorListener: (color: Int)->Unit) {
        ColorPickerDialog.Builder(context)
            .setOnDismissListener { dismissListener.invoke() }
            .setTitle(title)
            .setPreferenceName("MyColorPickerDialog")
            .setPositiveButton("적용", ColorEnvelopeListener { envelope, fromUser -> selectColorListener.invoke(envelope.color) })
            .setNegativeButton("취소") { dialogInterface, i -> dialogInterface.dismiss() }
            .attachAlphaSlideBar(true) // the default value is true.
            .attachBrightnessSlideBar(true) // the default value is true.
            .setBottomSpace(0) // set a bottom space between the last slidebar and buttons.
            .show()
    }
}