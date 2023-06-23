package com.eyedea.animax.di

import com.eyedea.animax.view_models.DashboardLandingViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun viewModelModules() = listOf(
    sharedSampleViewModels()
)

fun sharedSampleViewModels() = module {
    singleOf(::DashboardLandingViewModel)
}