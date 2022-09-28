package com.twaun95.signature.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.content.res.ResourcesCompat
import com.twaun95.signature.R
import com.twaun95.signature.common.Logger
import kotlin.math.abs

class DrawingView : View {
    constructor(context: Context) : super(context)
    constructor (context: Context, attrs : AttributeSet?) : super(context, attrs)
    constructor (context: Context, attrs : AttributeSet?, defStyleAttr : Int) : super (context, attrs, defStyleAttr)

    private val drawingPaths = mutableListOf<Path>()
    private lateinit var drawingCanvas: Canvas
    private lateinit var drawingBitmap: Bitmap

    private var penColor = ResourcesCompat.getColor(resources, R.color.color_pen, null)
    private var backgroundCanvasColor = ResourcesCompat.getColor(resources, R.color.white, null)
    private var penStrokeWidth = 12f

    private var penPaint = Paint().apply {
        color = penColor
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE // default: FILL
        strokeJoin = Paint.Join.ROUND // default: MITER
        strokeCap = Paint.Cap.ROUND // default: BUTT
        strokeWidth = penStrokeWidth // default: Hairline-width (얇게 처리)
    }

    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop

    private var currentX = 0f
    private var currentY = 0f
    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f

    /**
     * Size가 0이 아닐때, 호출되는 메소드. extraCanvas 를 그린다.
     */
    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)

        Logger.d("onSizeChanged")
        if (::drawingBitmap.isInitialized) drawingBitmap.recycle()

        drawingBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        drawingCanvas = Canvas(drawingBitmap)
        drawingCanvas.drawColor(backgroundCanvasColor)
    }



    override fun onDraw(canvas: Canvas) {
        Logger.d("onDraw")
        // Draw the bitmap that has the saved path.
        canvas.drawBitmap(drawingBitmap, 0f, 0f, null)
    }

    /**
     * touch 에 따른 event 처리.
     */
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        motionTouchEventX = event.x
        motionTouchEventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true
    }

    /**
     * 초기화 function. extraBitmap 초기화 & invalidate.
     */
    fun reset() {
        Logger.d("reset")

        if (::drawingBitmap.isInitialized) drawingBitmap.recycle()
        penPaint.color = Color.BLACK
        drawingBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        drawingCanvas = Canvas(drawingBitmap)
        drawingCanvas.drawColor(backgroundCanvasColor)
        drawingPaths.clear()

        invalidate()
    }



    /**
     * The following methods factor out what happens for different touch events,
     * as determined by the onTouchEvent() when statement.
     * This keeps the when conditional block
     * concise and makes it easier to change what happens for each event.
     * No need to call invalidate because we are not drawing anything.
     */
    private fun touchStart() {

        drawingPaths.add(Path().apply {
            moveTo(motionTouchEventX, motionTouchEventY)
        })

        currentX = motionTouchEventX
        currentY = motionTouchEventY
    }

    private fun touchMove() {
        val dx = abs(motionTouchEventX - currentX)
        val dy = abs(motionTouchEventY - currentY)
        if (dx >= touchTolerance || dy >= touchTolerance) {
            drawingPaths.last().quadTo(currentX, currentY, (motionTouchEventX + currentX) / 2, (motionTouchEventY + currentY) / 2)
            currentX = motionTouchEventX
            currentY = motionTouchEventY

            drawingPaths.forEach { drawingCanvas.drawPath(it, penPaint) }
        }

        invalidate()
    }

    private fun touchUp() {
        drawingPaths.last().reset()
    }

    fun changeBackgroundColor(color: Int) {
        backgroundCanvasColor = color
        drawingCanvas.drawColor(backgroundCanvasColor)
        invalidate()
    }

    fun changePenColor(penColor : Int) {
        Logger.d("changePenColor")
        penPaint.color = penColor
    }

    fun changeStrokeWidth(width: Float) {
        Logger.d("changeStrokeWidth")
        penStrokeWidth += 4f
        this.penPaint.strokeWidth = penStrokeWidth
    }

    fun erasingMode() {
        penPaint = Paint().apply {
            color = backgroundCanvasColor
            isAntiAlias = true
            isDither = true
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeWidth = 40f
        }
    }

    fun goBack() {
        drawingPaths.last().reset()
        drawingPaths.removeLast()
        invalidate()
    }

    fun goFront() {

    }
}