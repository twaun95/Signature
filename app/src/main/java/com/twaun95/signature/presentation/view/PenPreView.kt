package com.twaun95.signature.presentation.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.max

class PenPreView : View {
    constructor(context: Context) : super(context)
    constructor (context: Context, attrs : AttributeSet?) : super(context, attrs)
    constructor (context: Context, attrs : AttributeSet?, defStyleAttr : Int) : super (context, attrs, defStyleAttr)

    private var paint = Paint().apply {
        color = Color.BLACK
    }

    private var rect = RectF(0f,0f,0f,0f)
    private var viewHeight = 0
    private var viewWidth = 0
    private var penWidth = 0

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        viewHeight = h
        viewWidth = w

//        rect = Rect().apply {
//            left = w/2 - penWidth/2
//            right = w/2 + penWidth/2
//            top = 0
//            bottom = h
//        }
        rect = RectF().apply {
            left = (w/2 - penWidth/2).toFloat()
            right = (w/2 + penWidth/2).toFloat()
            top = 0f
            bottom = h.toFloat()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRoundRect(rect, max(rect.width(), rect.height()), max(rect.width(), rect.height()), paint)
//        canvas.drawLine(rect.left.toFloat(), rect.top.toFloat(), rect.right.toFloat(), rect.bottom.toFloat(), paint)
    }

    fun onWidthChanged(width: Int) {
        rect.apply {
            left = (viewWidth/2 - width/2).toFloat()
            right = (viewWidth/2 + width/2).toFloat()
        }
        invalidate()
    }


    fun initialize(width: Float, color: Int) {
        penWidth = width.toInt()
        paint.color = color

        invalidate()
    }
}