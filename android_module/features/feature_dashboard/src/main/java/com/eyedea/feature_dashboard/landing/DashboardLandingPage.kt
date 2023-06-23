package com.eyedea.feature_dashboard.landing

import android.view.View
import com.eyedea.animax.view_models.DashboardLandingViewModel
import com.eyedea.feature_config.base.BaseViewBindingFragment
import com.eyedea.feature_dashboard.R
import com.eyedea.feature_dashboard.databinding.DashboardLandingPageBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardLandingPage(
    override val layout: Int = R.layout.dashboard_landing_page
) : BaseViewBindingFragment<DashboardLandingPageBinding>() {

    private val viewModel by viewModel<DashboardLandingViewModel>()

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
        binding?.textSample?.setOnClickListener { viewModel.sampleApi.loadData((1..20).random()) }
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