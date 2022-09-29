package com.twaun95.signature.presentation.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class PenPreView : View {
    constructor(context: Context) : super(context)
    constructor (context: Context, attrs : AttributeSet?) : super(context, attrs)
    constructor (context: Context, attrs : AttributeSet?, defStyleAttr : Int) : super (context, attrs, defStyleAttr)

    private var paint = Paint().apply {
        color = Color.BLACK
    }

    private lateinit var rect : Rect
    private var viewHeight = 0
    private var viewWidth = 0

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        viewHeight = h
        viewWidth = w
        rect = Rect(0,0,w,h)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(rect, paint)
    }

    fun onWidthChanged(width: Int) {

        rect.apply {
            left = viewWidth/2 - width/2
            right = viewWidth/2 + width/2
        }
        invalidate()
    }
}