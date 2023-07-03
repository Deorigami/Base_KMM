package com.eyedea.feature_config.service

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class ClickerAccessibilityService : AccessibilityService() {
    override fun onAccessibilityEvent(p0: AccessibilityEvent?) {

    }

    override fun onInterrupt() {

    }

    // Simulate a touch event at the specified XY coordinate
    fun simulateTouch(x: Float, y: Float, callback: GestureResultCallback? = null) {
        val path = Path()
        path.moveTo(x, y)

        val gestureBuilder = GestureDescription.Builder()
        gestureBuilder.addStroke(GestureDescription.StrokeDescription(path, 0, 50))
        dispatchGesture(gestureBuilder.build(), callback, null)
    }
}