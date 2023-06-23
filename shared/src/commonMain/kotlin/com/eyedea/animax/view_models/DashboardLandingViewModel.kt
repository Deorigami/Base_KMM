package com.eyedea.animax.view_models

import com.ardinata.shared_core.base.BaseViewModel
import com.ardinata.shared_core.base.StatefulData
import com.eyedea.shared_sample.domain.use_case.SampleSharedUseCase

class DashboardLandingViewModel(
    sampleSharedUseCase: SampleSharedUseCase
) : BaseViewModel() {

    val sampleApi = StatefulData(
        sampleSharedUseCase,
        scope
    )

    init {
        sampleApi.loadData(1)
    }

}