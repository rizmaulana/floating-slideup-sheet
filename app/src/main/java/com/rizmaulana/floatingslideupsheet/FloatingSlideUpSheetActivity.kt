package com.rizmaulana.floatingslideupsheet

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import com.google.android.material.bottomsheet.BottomSheetBehavior
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

    private val bottomSheetBehaviour by lazy {
        BottomSheetBehavior.from(layout_expand).apply {
            setBottomSheetCallback(bottomSheetBehaviorCallback)
            skipCollapsed = true
            isHideable = true
            isFitToContents = false
        }

    }

    private val bottomSheetBehaviorCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(bottomSheet: View, slideOffset: Float) {
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

        /*hide opacity = 0, visible 1.0
        * Card visible qOp = 0, invisible qOp = 1.0
        * 20%
        *
        * 20/100
        * */
        val cardOpacity = 1f.minus(opacity)
        val twentyPercentOpacity = cardOpacity.minus(0.8f).times(5f)

        Log.d("RizkiM", "point $point opacityExpand $opacity opacityCard $cardOpacity QuartOp $twentyPercentOpacity")

        container_floating_menu.alpha = twentyPercentOpacity

        if (point > 20) {
            content_expand_container.visibility = View.VISIBLE
            card_floating_menu.visibility = View.INVISIBLE
        } else {
            content_expand_container.visibility = View.INVISIBLE
            card_floating_menu.visibility = View.VISIBLE
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        collapseBottomSheet()
    }

    private fun initView() {
        card_floating_menu.setOnClickListener {
            expandBottomSheet()
        }

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
        bottomSheetBehaviour.state = BottomSheetBehavior.STATE_HIDDEN
    }

    private fun expandBottomSheet() {
        bottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
    }

    companion object {
        const val COLOR_BLACK = "#000000"
    }
}