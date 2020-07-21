package com.rizmaulana.floatingslideupsheet

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import com.google.android.material.bottomsheet.BottomSheetBehavior
import id.rizmaulana.floatingslideupsheet.view.FloatingSlideUpBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_expand_menu.*
import kotlinx.android.synthetic.main.layout_floating_menu.*


class FloatingSlideUpSheetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }


    private fun initView() {
        FloatingSlideUpBuilder(this, slide_conten)
            .setFloatingMenuRadiusInDp(64)
            .setFloatingMenu(first)
            .setPanel(layout_expand)
            .build()

    }

}