package com.twaun95.signature.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.twaun95.signature.R
import com.twaun95.signature.common.Logger

class BackgroundView : View {
    constructor(context: Context) : super(context)
    constructor (context: Context, attrs : AttributeSet?) : super(context, attrs)
    constructor (context: Context, attrs : AttributeSet?, defStyleAttr : Int) : super (context, attrs, defStyleAttr)


    private var isInitialized = false
    private lateinit var backgroundBitmap: Bitmap
    private lateinit var backgroundCanvas: Canvas

    private var paint = Paint().apply {
        color = Color.BLUE
    }

    private var backgroundCanvasColor = ResourcesCompat.getColor(resources, R.color.white, null)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }

    private fun initialize(canvas: Canvas) {
        backgroundBitmap = Bitmap.createBitmap(canvas.width, canvas.height, Bitmap.Config.ARGB_8888)
        backgroundCanvas = Canvas(backgroundBitmap)
        backgroundCanvas.drawColor(backgroundCanvasColor)
        Logger.d("width: ${canvas.width} height: ${canvas.height}")

        isInitialized = true
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (!isInitialized) initialize(canvas)

        canvas.drawBitmap(backgroundBitmap, 0f, 0f, null)
    }

    fun changeColor(color: Int) {
        backgroundCanvasColor = color
        backgroundCanvas.drawColor(backgroundCanvasColor)
        invalidate()
    }

    fun reset() {
        changeColor(ResourcesCompat.getColor(resources, R.color.white, null))
    }

    fun getColor() = backgroundCanvasColor
}