package com.twaun95.signature.presentation.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.max

class EraserPreView : View {
    constructor(context: Context) : super(context)
    constructor (context: Context, attrs : AttributeSet?) : super(context, attrs)
    constructor (context: Context, attrs : AttributeSet?, defStyleAttr : Int) : super (context, attrs, defStyleAttr)

    private var paint = Paint().apply {
        color = Color.WHITE
    }

    private lateinit var rect : RectF
    private var viewHeight = 0
    private var viewWidth = 0
    private var penWidth = 0

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        viewHeight = h
        viewWidth = w

        rect = RectF().apply {
            left = (w/2 - penWidth/2).toFloat()
            right = (w/2 + penWidth/2).toFloat()
            top = 0f
            bottom = h.toFloat()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRoundRect(rect, max(rect.width(), rect.height())*0.1f, max(rect.width(), rect.height())*0.1f, paint)
    }

    fun onWidthChanged(width: Int) {
        rect.apply {
            left = (viewWidth/2 - width/2).toFloat()
            right = (viewWidth/2 + width/2).toFloat()
        }
        invalidate()
    }


    fun initialize(width: Float) {
        penWidth = width.toInt()
        invalidate()
    }
}