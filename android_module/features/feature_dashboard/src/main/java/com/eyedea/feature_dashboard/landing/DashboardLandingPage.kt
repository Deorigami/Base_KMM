package com.eyedea.feature_dashboard.landing

import android.view.View
import com.eyedea.feature_config.base.BaseViewBindingFragment
import com.eyedea.feature_dashboard.R
import com.eyedea.feature_dashboard.databinding.DashboardLandingPageBinding

class DashboardLandingPage(
    override val layout: Int = R.layout.dashboard_landing_page
) : BaseViewBindingFragment<DashboardLandingPageBinding>() {

    override fun initBinding(view: View) {
        binding = DashboardLandingPageBinding.bind(view)
    }

    override val TAG: String?
        get() = "Test"


}