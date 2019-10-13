package com.anwesh.uiprojects.branchgrowview

/**
 * Created by anweshmishra on 13/10/19.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.app.Activity
import android.content.Context

val nodes : Int = 5
val scGap : Float = 0.02f
val strokeFactor : Int = 90
val circleSizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#00C853")
val backColor : Int = Color.parseColor("#BDBDBD")
val delay : Long = 20

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n

fun Canvas.drawBranchGrow(sf : Float, size : Float, sc : Float, paint : Paint) {
    val sc1 : Float = sc.divideScale(0, 2)
    val sc2 : Float = sc.divideScale(1, 2)
    val r : Float = size / circleSizeFactor
    save()
    translate(0f, size / 2)
    drawLine(0f, 0f, size * sf * sc1, 0f, paint)
    save()
    translate(0f, size * sf)
    drawCircle(0f, 0f, r * sc2, paint)
    restore()
    restore()
}

fun Canvas.drawBGNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / nodes
    paint.color = foreColor
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.strokeCap = Paint.Cap.ROUND
    save()
    translate(w / 2, gap * i)
    drawLine(0f, 0f, 0f, gap + gap * scale, paint)
    drawBranchGrow(1f - 2 * (i % 2), gap, scale, paint)
    restore()
}

class BranchGrowView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }

    data class State(var scale : Float = 0f, var dir : Float = 0f, var prevScale : Float = 0f) {

        fun update(cb : (Float) -> Unit) {
            scale += dir * scGap
            if (Math.abs(scale - prevScale) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                cb(prevScale)
            }
        }

        fun startUpdating(cb : () -> Unit) {
            if (dir == 0f) {
                dir = 1f - 2 * prevScale
                cb()
            }
        }
    }
}
