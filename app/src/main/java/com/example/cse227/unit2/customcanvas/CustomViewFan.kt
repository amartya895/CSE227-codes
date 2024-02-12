package com.example.cse227.unit2.customcanvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class CustomViewFan(context : Context?) : View(context) {
    var p : Paint=Paint()
    var x=100
    var y = 10
    override fun onDraw(canvas : Canvas) {
        canvas.drawColor(Color.BLACK)
        p.color=Color.GRAY
        canvas.drawRect(100f , 100f , 500f , 500f , p)
        p.color=Color.GRAY
        canvas.drawArc(
                500f , 500f , 800f , 800f , x.toFloat() ,
                30f , true , p
        )
        canvas.drawArc(
                500f , 500f , 800f , 800f , (x + 120).toFloat() ,
                30f , true , p
        )
        canvas.drawArc(
                500f , 500f , 800f , 800f , (x + 240).toFloat() ,
                30f , true , p
        )
        canvas.drawOval(200f,400f , 800f ,600f , p)
    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> startFan()
            MotionEvent.ACTION_UP -> stopFan()
        }
        return true
    }

    fun stopFan() {
    }

    fun startFan() {
        x=x + 5
        invalidate()
    }
    fun startMove(){
        y+=10
        invalidate()
    }
}