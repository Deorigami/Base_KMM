package com.eyedea.app.di.module

import com.eyedea.animax.view_models.DashboardLandingViewModel
import com.eyedea.app.di.router.FeatureDashboardContractImpl
import com.eyedea.feature_dashboard.FeatureDashboardRouterContract
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

fun androidModule() = module {
    single<FeatureDashboardRouterContract>{ FeatureDashboardContractImpl() }
}