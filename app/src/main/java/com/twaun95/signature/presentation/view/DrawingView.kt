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

class DrawingView : View {
    constructor(context: Context) : super(context)
    constructor (context: Context, attrs : AttributeSet?) : super(context, attrs)
    constructor (context: Context, attrs : AttributeSet?, defStyleAttr : Int) : super (context, attrs, defStyleAttr)

    private var path = Path()
    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap
    private lateinit var frame: Rect

    private var penColor = ResourcesCompat.getColor(resources, R.color.color_pen, null)
    private var backgroundCanvasColor = ResourcesCompat.getColor(resources, R.color.white, null)
    private var penStrokeWidth = 12f

    private var paint = Paint().apply {
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

        if (::extraBitmap.isInitialized) extraBitmap.recycle()
        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundCanvasColor)
    }

    override fun onDraw(canvas: Canvas) {
        // Draw the bitmap that has the saved path.
        canvas.drawBitmap(extraBitmap, 0f, 0f, null)
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
        if (::extraBitmap.isInitialized) extraBitmap.recycle()
        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundCanvasColor)

        // Calculate a rectangular frame around the picture.
//        val inset = 40
//        frame = Rect(inset, inset, width - inset, height - inset)

        // 다시 그린다.
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

        path.reset()
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
    }

    private fun touchMove() {
        val dx = Math.abs(motionTouchEventX - currentX)
        val dy = Math.abs(motionTouchEventY - currentY)
        if (dx >= touchTolerance || dy >= touchTolerance) {
            // QuadTo() adds a quadratic bezier from the last point,
            // approaching control point (x1,y1), and ending at (x2,y2).
            path.quadTo(currentX, currentY, (motionTouchEventX + currentX) / 2, (motionTouchEventY + currentY) / 2)
            currentX = motionTouchEventX
            currentY = motionTouchEventY
            // Draw the path in the extra bitmap to save it.
            extraCanvas.drawPath(path, paint)
        }
        // Invalidate() is inside the touchMove() under ACTION_MOVE because there are many other
        // types of motion events passed into this listener, and we don't want to invalidate the
        // view for those.
        invalidate()
    }

    private fun touchUp() {
        // Reset the path so it doesn't get drawn again.
        path.reset()
    }

    /**
     * 펜 색 변경.
     */
    fun changePenColor(penColor : Int) {
//        this.penColor = ResourcesCompat.getColor(resources, penColor, null)

        paint = Paint().apply {
            color = penColor
            isAntiAlias = true
            isDither = true
            style = Paint.Style.STROKE // default: FILL
            strokeJoin = Paint.Join.ROUND // default: MITER
            strokeCap = Paint.Cap.ROUND // default: BUTT
            strokeWidth = penStrokeWidth // default: Hairline-width (얇게 처리)
        }
    }

    fun changeStrokeWidth(width: Float) {
        penStrokeWidth += 4f
        this.paint.strokeWidth = penStrokeWidth

    }

    fun changeBackgroundColor(backgroundCanvasColor : Int) {
        this.backgroundCanvasColor = backgroundCanvasColor
        extraCanvas.drawColor(backgroundCanvasColor)
    }

}