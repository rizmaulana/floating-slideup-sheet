package id.rizmaulana.floatingslideupsheet.listener

import android.content.Context
import android.view.GestureDetector

/**
 * rizmaulana 21/07/20.
 */

class GestureDragDetector(
    private val context: Context,
    private val listener: GestureDragListener
) : GestureDetector(context, listener) {


    fun cancel() {
        listener.cancel()
    }

}