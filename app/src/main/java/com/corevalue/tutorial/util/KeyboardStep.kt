package com.corevalue.tutorial.util

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ScrollView
import androidx.annotation.RequiresApi
import androidx.core.widget.NestedScrollView
import kotlin.math.abs

fun setUpKeyboardListener(scrollView: ScrollView, notNeedFocus: Boolean = false) {
    if (!notNeedFocus) {
        scrollView.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            scrollView.getWindowVisibleDisplayFrame(r)
            if (abs(scrollView.rootView.height - (r.bottom - r.top)) > 100) { // if more than 100 pixels, its probably a keyboard...
                onKeyboardShow(scrollView)
            }
        }
    }

}

private fun onKeyboardShow(scrollView: ScrollView) {
    scrollView.scrollToBottomWithoutFocusChange()
}

fun setUpKeyboardListener(scrollView: NestedScrollView, notNeedFocus: Boolean = false) {
    if (!notNeedFocus) {
        scrollView.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            scrollView.getWindowVisibleDisplayFrame(r)
            if (abs(scrollView.rootView.height - (r.bottom - r.top)) > 100) { // if more than 100 pixels, its probably a keyboard...
                onKeyboardShow(scrollView)
            }
        }
    }

}

private fun onKeyboardShow(scrollView: NestedScrollView) {
    scrollView.fullScroll(100)
}

private fun ScrollView.scrollToBottomWithoutFocusChange() { // Kotlin extension to scrollView
    val lastChild = getChildAt(childCount - 1)
    val bottom = lastChild.bottom + paddingBottom
    val delta = bottom - (scrollY + height)
    Log.d("YSADGSDGSDFGsd", "scrollToBottomWithoutFocusChange:  $delta")
    fullScroll(View.FOCUS_DOWN)
}


fun Int.dpToPx(context: Context): Int {
    return (this * context.resources.displayMetrics.density).toInt()
}

fun setUpKeyboardVisibilityListener(scrollView: ScrollView, targetView: View, paddingWhenKeyboardVisible: Int = 333) {
    val rootView = scrollView.rootView
    rootView.viewTreeObserver.addOnGlobalLayoutListener {
        val rect = Rect()
        rootView.getWindowVisibleDisplayFrame(rect)

        val screenHeight = rootView.height
        val keyboardHeight = screenHeight - rect.bottom

        if (keyboardHeight > screenHeight * 0.15) {
            // Keyboard is visible
            Log.d("YATTASTASTAS", "setUpKeyboardVisibilityListener: ")
            scrollView.setPadding(0, 0, 0, paddingWhenKeyboardVisible)
            scrollView.post {
                scrollView.smoothScrollTo(0, targetView.bottom)
            }
        } else {
            // Keyboard is hidden
            scrollView.setPadding(0, 0, 0, 0)
        }
    }
}
