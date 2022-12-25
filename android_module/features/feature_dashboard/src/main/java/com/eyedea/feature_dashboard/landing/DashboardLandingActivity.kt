package com.eyedea.feature_dashboard.landing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eyedea.feature_dashboard.R

class DashboardLandingActivity(
    layout: Int = com.eyedea.feature_config.R.layout.activity_base_fragment
) : AppCompatActivity(layout) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.eyedea.feature_config.R.layout.activity_base_fragment)

        val fragment = DashboardLandingPage()
        fragment.arguments = intent.extras
        supportFragmentManager.beginTransaction()
            .replace(com.eyedea.feature_config.R.id.mainFragment, fragment)
            .commit()
        overridePendingTransition(com.eyedea.ui_component.R.anim.slide_in_from_right, com.eyedea.ui_component.R.anim.slide_out_to_left)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(com.eyedea.ui_component.R.anim.slide_in_from_left, com.eyedea.ui_component.R.anim.slide_out_to_right)
    }

    companion object {
        const val FROM_TOKEN_EXPIRED = "FROM_TOKEN_EXPIRED"
    }
}