package com.eyedea.animax.di

import com.eyedea.shared_sample.sharedSampleModule
import org.koin.core.module.Module

fun appModule() = listOf(
    sharedSampleModule(),
    viewModelModules()
).flatten()