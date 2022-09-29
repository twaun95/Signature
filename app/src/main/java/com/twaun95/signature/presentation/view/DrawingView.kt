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

    companion object {
        const val DEFAULT_PEN_WIDTH = 12f
        const val DEFAULT_ERASER_WIDTH = 14f
        const val DEFAULT_PEN_COLOR = Color.BLACK
        const val DEFAULT_BACKGROUND_COLOR = Color.WHITE
    }
    private val drawingPaths = mutableListOf<Path>()

    private lateinit var drawingCanvas: Canvas
    private lateinit var drawingBitmap: Bitmap

    private var penColor = DEFAULT_PEN_COLOR
    private var backgroundCanvasColor = DEFAULT_BACKGROUND_COLOR
    private var penStrokeWidth = DEFAULT_PEN_WIDTH
    private var eraserWidth = DEFAULT_ERASER_WIDTH

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
        drawingPaths.forEach { drawingCanvas.drawPath(it, penPaint) }
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

        backgroundCanvasColor = DEFAULT_BACKGROUND_COLOR
        penPaint.apply {
            color = DEFAULT_PEN_COLOR
            strokeWidth = DEFAULT_PEN_WIDTH
        }

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
        this.penColor = penColor
        penPaint.color = this.penColor
    }

    fun changeStrokeWidth(width: Float) {
        Logger.d("changeStrokeWidth")
        penStrokeWidth = width
        this.penPaint.strokeWidth = penStrokeWidth
    }

    fun changeEraserStrokeWidth(width: Float) {
        Logger.d("changeStrokeWidth")
        eraserWidth = width
        this.penPaint.strokeWidth = eraserWidth
    }

    fun erasingMode(isEraser: Boolean) {
        penPaint = Paint().apply {
            color = if (isEraser) backgroundCanvasColor else penColor
            isAntiAlias = true
            isDither = true
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeWidth = if (isEraser) eraserWidth else penStrokeWidth
        }
    }

    fun goBack() {


        drawingPaths.removeLast()
        drawingPaths.forEach { drawingCanvas.drawPath(it, penPaint) }
        invalidate()
    }

    fun goFront() {

    }

    fun getBitmap() : Bitmap{
        return drawingBitmap
    }

    fun getPenWidth() : Float {
        return penPaint.strokeWidth
    }

    fun getPenColor() : Int {
        return penColor
    }
}