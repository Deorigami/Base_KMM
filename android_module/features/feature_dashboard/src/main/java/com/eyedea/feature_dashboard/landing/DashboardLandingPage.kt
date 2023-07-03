package com.eyedea.feature_dashboard.landing

import android.accessibilityservice.GestureDescription
import android.util.Log
import android.accessibilityservice.AccessibilityService.GestureResultCallback
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.activity.result.contract.ActivityResultContracts
import com.eyedea.animax.view_models.DashboardLandingViewModel
import com.eyedea.feature_config.base.BaseViewBindingFragment
import com.eyedea.feature_config.service.ClickerAccessibilityService
import com.eyedea.feature_dashboard.BuildConfig
import com.eyedea.feature_dashboard.R
import com.eyedea.feature_dashboard.databinding.DashboardLandingPageBinding
import com.eyedea.ui_component.view.template.RadioButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardLandingPage(
    override val layout: Int = R.layout.dashboard_landing_page
) : BaseViewBindingFragment<DashboardLandingPageBinding>() {

    private val viewModel by viewModel<DashboardLandingViewModel>()
    private val accessibility = ClickerAccessibilityService()
    private val windowManager by lazy { requireActivity().getSystemService(Context.WINDOW_SERVICE) as WindowManager }
    private val systemAlertWindowPermissionLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

    }
    private var initialX = 0
    private var initialY = 0
    private var initialTouchX = 0f
    private var initialTouchY = 0f
    private var isDragging = false

    override fun initBinding(view: View) {
        binding = DashboardLandingPageBinding.bind(view)
    }

    override val TAG: String?
        get() = "Test"

    override fun didMount(view: View) {
        super.didMount(view)
        setObserver()
        setupListener()
    }

    private fun setupListener() {
        binding?.textSample?.setOnClickListener {
//            viewModel.sampleApi.loadData((1..20).random())
            checkSystemAlertWindowPermission()
        }
    }

    private fun checkSystemAlertWindowPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(requireContext())) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
            intent.data = Uri.parse("package:${requireActivity().packageName}")
            systemAlertWindowPermissionLauncher.launch(intent)
        } else {
            // Permission already granted or running on a lower Android version
            // Show the floating view here
            showFloatingView()
        }
        showFloatingView()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showFloatingView() {
        // Set the layout parameters for the floating view
        val layoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
                else -> WindowManager.LayoutParams.TYPE_PHONE
            },
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        // Set the position of the floating view
        layoutParams.gravity = Gravity.TOP or Gravity.START
        layoutParams.x = 0
        layoutParams.y = 0

        val radioButton = RadioButton(requireContext())
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        // Add touch listener to enable dragging
        radioButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    initialX = layoutParams.x
                    initialY = layoutParams.y
                    initialTouchX = event.rawX
                    initialTouchY = event.rawY
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    val dx = event.rawX - initialTouchX
                    val dy = event.rawY - initialTouchY
                    layoutParams.x = (initialX + dx).toInt()
                    layoutParams.y = (initialY + dy).toInt()
                    windowManager.updateViewLayout(view, layoutParams)
                    isDragging = true
                    true
                }
                MotionEvent.ACTION_UP -> {
                    if (!isDragging) radioButton.isActive = !radioButton.isActive
                    isDragging = false
                    val maxX = screenWidth - view.width
                    val releaseX = layoutParams.x

                    // Calculate the target x-coordinate based on the release position
                    val targetX = when {
                        releaseX > maxX / 2 -> maxX
                        else -> 0
                    }

                    // Animate the movement to the target position
                    val animator = ValueAnimator.ofInt(layoutParams.x, targetX)
                    animator.addUpdateListener { valueAnimator ->
                        layoutParams.x = valueAnimator.animatedValue as Int
                        windowManager.updateViewLayout(view, layoutParams)
                    }
                    animator.interpolator = AccelerateDecelerateInterpolator()
                    animator.duration = 300 // Animation duration in milliseconds
                    animator.start()
                    true
                }
                else -> view.performClick()
            }
        }

        // Add the floating view to the WindowManager
        windowManager.addView(radioButton, layoutParams)
    }



    private fun setObserver() {
        viewModel.run {
            sampleApi.listen(
                onSuccess = { binding?.textSample?.text = it.toString() },
                onError = {
                    binding?.textSample?.text = it.message
                }
            )
        }
    }



}