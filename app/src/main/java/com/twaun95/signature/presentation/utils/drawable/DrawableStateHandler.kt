package com.twaun95.signature.presentation.utils.drawable

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import androidx.annotation.DrawableRes

object DrawableStateHandler {

    fun getPressedDrawable(default : Drawable, pressed : Drawable) : Drawable {
        return StateListDrawable().apply {
            this.addState(intArrayOf(android.R.attr.state_pressed) , pressed)
            this.addState(intArrayOf(android.R.attr.state_enabled) , default)
        }
    }

    fun getCheckDrawable(default : Drawable, checked : Drawable) : Drawable {
        return StateListDrawable().apply {
            this.addState(intArrayOf(android.R.attr.state_checked) , checked)
            this.addState(intArrayOf(android.R.attr.state_enabled) , default)
        }
    }
}