package com.cna.loadingdots

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class LoadingDots: View {

    private var circlePaint = Paint()
    private var circleRadius = 5f
    private var targetRadius = 10f
    private var animationDuration = 500L
    private var numCircles = 3
    private var circleColor = Color.WHITE
    private val animators = mutableListOf<ValueAnimator>()
    private val radiusList: MutableList<Float> by lazy { MutableList(numCircles) { circleRadius } }

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    /**
     * Initializes the view by configuring the paint object, applying custom XML attributes,
     * and preparing the animation objects (ValueAnimators) for each circle.
     *
     * @param attrs The AttributeSet passed in from XML, which may contain custom attributes.
     */
    private fun init(attrs: AttributeSet?) {
        circlePaint.isAntiAlias = true
        circlePaint.color = Color.WHITE
        circlePaint.style = Paint.Style.FILL

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.LoadingDots)
            numCircles = typedArray.getInt(R.styleable.LoadingDots_numCircles, numCircles)
            animationDuration = typedArray.getInt(R.styleable.LoadingDots_animationDuration, animationDuration.toInt()).toLong()
            circleColor = typedArray.getColor(R.styleable.LoadingDots_circleColor, circleColor)
            typedArray.recycle()
        }

        radiusList.clear()
        radiusList.addAll(MutableList(numCircles) {circleRadius} )
        animators.clear()

        for (i in 0 until numCircles) {
            val animator = createValueAnimator(i)
            animators.add(animator)
        }
    }


    /**
     * Initializes the LoadingDots custom view by reading custom XML attributes.
     * Retrieves the **number of circles**, **animation duration**, and **circle color**
     * from the provided AttributeSet, and applies these values to the view.
     * **If any attribute is not specified, it uses the existing default values.**
     *
     * @param attrs The **AttributeSet** containing the custom XML attributes for the view.
     */
    private fun createValueAnimator(index: Int): ValueAnimator {
        val animator = ValueAnimator.ofFloat(circleRadius, targetRadius, circleRadius)
        animator.duration = animationDuration
        animator.repeatMode = ValueAnimator.RESTART
        animator.repeatCount = ValueAnimator.INFINITE
        animator.startDelay = index * (animationDuration / numCircles)
        animator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            radiusList[index] = animatedValue

            circlePaint.color = circleColor


            postInvalidate()
        }
        animator.start()
        return animator
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in 0 until numCircles) {
            drawCircle(canvas, i)
        }
    }

    private fun drawCircle(canvas: Canvas, index: Int) {
        val x = (2 * index + 1) * targetRadius
        val y = height / 2f
        val radius = radiusList[index]
        canvas.drawCircle(x, y, radius, circlePaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = (2 * numCircles * targetRadius).toInt()
        val desiredHeight = (2 * targetRadius).toInt()
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val finalWidth = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> desiredWidth.coerceAtMost(widthSize)
            else -> desiredWidth
        }

        val finalHeight = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> desiredHeight.coerceAtMost(heightSize)
            else -> desiredHeight
        }

        setMeasuredDimension(finalWidth, finalHeight)
    }
}