package com.eyedea.ui_component.view.util

import android.content.Context

object ResolutionUtils {
    fun Int.convertDpToPx(context: Context): Float {
        return this * context.resources.displayMetrics.density

    }

    fun Int.dp(context: Context): Int {
        return this * context.resources.displayMetrics.density.toInt()
    }
}