package com.eyedea.feature_dashboard.landing

import android.view.View
import androidx.fragment.app.viewModels
import com.eyedea.feature_config.base.BaseViewBindingFragment
import com.eyedea.feature_dashboard.R
import com.eyedea.feature_dashboard.databinding.DashboardLandingPageBinding

class DashboardLandingPage(
    override val layout: Int = R.layout.dashboard_landing_page
) : BaseViewBindingFragment<DashboardLandingPageBinding>() {

    private val viewModel by viewModels<DashboardLandingViewModel>()

    override fun initBinding(view: View) {
        binding = DashboardLandingPageBinding.bind(view)
    }

    override val TAG: String?
        get() = "Test"

    override fun didMount(view: View) {
        super.didMount(view)
        setObserver()
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