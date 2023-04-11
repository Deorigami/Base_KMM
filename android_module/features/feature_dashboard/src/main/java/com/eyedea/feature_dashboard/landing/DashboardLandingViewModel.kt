package com.eyedea.feature_dashboard.landing

import androidx.lifecycle.viewModelScope
import com.ardinata.shared_core.base.BaseViewModel
import com.ardinata.shared_core.base.StatefulData
import com.eyedea.shared_sample.domain.use_case.SampleSharedUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DashboardLandingViewModel : BaseViewModel(), KoinComponent {
    private val sampleBaseUseCase by inject<SampleSharedUseCase>()

    val sampleApi = StatefulData(
        sampleBaseUseCase,
        scope
    )

    init {
        sampleApi.loadData(1)
    }

}