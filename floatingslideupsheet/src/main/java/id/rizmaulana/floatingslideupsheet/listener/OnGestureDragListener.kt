package id.rizmaulana.floatingslideupsheet.listener

/**
 * rizmaulana 21/07/20.
 */

interface OnGestureDragListener {

    fun onStartMove()

    fun onYMove(distance: Float, totalDistance: Float, x: Float, y: Float)

    fun onCancel(totalDistance: Float)
}