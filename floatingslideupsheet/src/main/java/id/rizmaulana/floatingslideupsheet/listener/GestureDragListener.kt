package id.rizmaulana.floatingslideupsheet.listener

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import id.rizmaulana.floatingslideupsheet.helper.orZero

/**
 * rizmaulana 21/07/20.
 */

class GestureDragListener(private val onDragListener: OnGestureDragListener) :
    GestureDetector.SimpleOnGestureListener() {

    private var totalDistance = 0f
    private var moving = false


    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        if (!moving) {
            onDragListener.onStartMove()
            moving = true
        }
        totalDistance += distanceY
        onDragListener.onYMove(distanceY, totalDistance, e1?.rawX.orZero(), e1?.rawY.orZero())
        return super.onScroll(e1, e2, distanceX, distanceY)
    }

    fun cancel() {
        onDragListener.onCancel(totalDistance)
        totalDistance = 0f
        moving = false
    }


}

