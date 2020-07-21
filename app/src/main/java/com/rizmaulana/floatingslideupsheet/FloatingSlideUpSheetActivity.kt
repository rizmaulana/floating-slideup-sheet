package com.rizmaulana.floatingslideupsheet

import android.content.ClipData
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.View.DragShadowBuilder
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import com.google.android.material.bottomsheet.BottomSheetBehavior
import id.rizmaulana.floatingslideupsheet.listener.GestureDragDetector
import id.rizmaulana.floatingslideupsheet.listener.GestureDragListener
import id.rizmaulana.floatingslideupsheet.listener.OnGestureDragListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_expand_menu.*
import kotlinx.android.synthetic.main.layout_floating_menu.*


class FloatingSlideUpSheetActivity : AppCompatActivity() {

    private val parentLayout by lazy {
        layout_expand.parent as CoordinatorLayout
    }

    private val defaultPaddingBottom by lazy {
        parentLayout.paddingBottom
    }

    private val gestureDetector by lazy {
        GestureDragDetector(this, GestureDragListener(object : OnGestureDragListener {

            override fun onStartMove() {
                bottomSheetBehaviorCallback.onStateChanged(
                    layout_expand,
                    BottomSheetBehavior.STATE_DRAGGING
                )


            }

            override fun onYMove(distance: Float, totalDistance: Float, x: Float, y: Float) {
            //    bottomSheetBehaviorCallback.onSlide(layout_expand, -0.01f)
               // bottomSheetBehaviour.setPeekHeight(totalDistance.toInt(), true)
             //   bottomSheetBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED

            }

            override fun onCancel(totalDistance: Float) {
                if (totalDistance > card_floating_menu.height) {
                    expandBottomSheet()
                } else {
                    collapseBottomSheet()
                }
            }
        }))
    }

    private val bottomSheetBehaviour by lazy {
        BottomSheetBehavior.from(layout_expand).apply {
            setBottomSheetCallback(bottomSheetBehaviorCallback)
          //  skipCollapsed = true


         //   isHideable = true

            halfExpandedRatio = 0.999999f
            peekHeight = 200
            isFitToContents = false
        }

    }

    private val bottomSheetBehaviorCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            Log.d("RizkiM", "Offset ${slideOffset.toString()}")

            /*-1.0 = Hide*/
            /*1.0 = Expanded*/

            /*range = 1.0 to 2.0*/
            /*pointPercent between 0 - 100*/
            val pointPercent = (slideOffset.plus(1)).div(2).times(100)
            val alpha = pointPercent.div(2).toInt()
            listenSlideChangeColor(alpha)
            listenSlidePadding(pointPercent.toInt())
            listenSlideAlphaContent(pointPercent.toInt())
        }

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            Log.d("RizkiM", "New state ${newState}")


        }
    }

    private fun listenSlideChangeColor(alpha: Int) {
        parentLayout.setBackgroundColor(Color.parseColor(setColorAlpha(alpha)))
    }

    private fun listenSlidePadding(point: Int) {
        card_floating_menu.post {
            val marginLeft = card_floating_menu.marginLeft
            val marginRight = card_floating_menu.marginRight

            /*0 Hide - 100 Expanded*/
            val dynamicLeftPadding =
                marginLeft.minus(((marginLeft.toFloat().div(100)).times(point))).toInt()
            val dynamicRightPadding =
                marginRight.minus(((marginRight.toFloat().div(100)).times(point))).toInt()
            val dynamicBottomPadding =
                defaultPaddingBottom.minus(((defaultPaddingBottom.toFloat().div(100)).times(point)))
                    .toInt()

            layout_expand.setPadding(dynamicLeftPadding, 0, dynamicRightPadding, 0)
            parentLayout.setPadding(
                parentLayout.paddingLeft,
                parentLayout.paddingTop,
                parentLayout.paddingRight,
                dynamicBottomPadding
            )


        }

    }

    private fun listenSlideAlphaContent(point: Int) {
        val opacity = point.toFloat().div(100)
        content_expand_container.alpha = opacity

        val cardOpacity = 1f.minus(opacity)
        val twentyPercentOpacity = cardOpacity.minus(0.8f).times(5f)

        container_floating_menu.alpha = twentyPercentOpacity

        if (point > 20) {
            content_expand_container.visibility = View.VISIBLE
            card_floating_menu.visibility = View.INVISIBLE
        } else {
            content_expand_container.visibility = View.INVISIBLE
            card_floating_menu.visibility = View.VISIBLE
        }
        if (point < 10) {
            layout_expand.visibility = View.INVISIBLE
        } else {
            layout_expand.visibility = View.VISIBLE
        }
        Log.d("RizkiM", "Point $point")

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        collapseBottomSheet()
    }

    private fun initView() {
       /* card_floating_menu.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                gestureDetector.cancel()
            }
            gestureDetector.onTouchEvent(event)
            true
        }*/

       /* card_floating_menu.setOnClickListener {
            bottomSheetBehaviour.setPeekHeight(100, true)
            bottomSheetBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
        }*/


    }

    private fun setColorAlpha(percentage: Int): String {
        val decValue = percentage.toDouble() / 100 * 255
        val rawHexColor = COLOR_BLACK.replace("#", "")
        val str = StringBuilder(rawHexColor)
        if (Integer.toHexString(decValue.toInt()).length == 1) str.insert(
            0,
            "#0" + Integer.toHexString(decValue.toInt())
        ) else str.insert(0, "#" + Integer.toHexString(decValue.toInt()))
        return str.toString()
    }

    private fun collapseBottomSheet() {
        bottomSheetBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun expandBottomSheet() {
        bottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
    }

    companion object {
        const val COLOR_BLACK = "#000000"
    }
}