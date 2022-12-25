package com.eyedea.animax.helper

import com.eyedea.animax.di.appModule
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(appModule())
    }
}