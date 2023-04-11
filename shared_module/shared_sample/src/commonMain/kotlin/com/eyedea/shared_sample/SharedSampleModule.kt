package com.eyedea.shared_sample

import com.eyedea.shared_sample.data.repository.SampleServiceRepositoryImpl
import com.eyedea.shared_sample.domain.repository.SampleServiceRepository
import com.eyedea.shared_sample.domain.use_case.SampleSharedUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun sharedSampleModule() = listOf(
    useCaseModule(),
    repositoryModule()
)

fun useCaseModule() = module {
    singleOf(::SampleSharedUseCase)
}

fun repositoryModule() = module {
    singleOf(::SampleServiceRepositoryImpl){
        bind<SampleServiceRepository>()
        createdAtStart()
    }
}