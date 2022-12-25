package com.eyedea.app.di.module

import com.eyedea.app.di.router.FeatureDashboardContractImpl
import com.eyedea.feature_dashboard.FeatureDashboardRouterContract
import org.koin.dsl.module

fun androidModule() = module {
    single<FeatureDashboardRouterContract>{ FeatureDashboardContractImpl() }
}