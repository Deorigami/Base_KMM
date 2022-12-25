package com.eyedea.app.di

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.ardinata.shared_core.KtorClientEngine
import com.eyedea.app.di.module.androidModule
import com.eyedea.animax.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        KtorClientEngine.Factory.context = applicationContext
        startKoin {
            modules(appModule() + androidModule())
            androidContext(baseContext)
            this.logger(object : Logger() {
                override fun display(level: Level, msg: MESSAGE) {
                    Log.d("ANGGATAG", "$level: $msg");
                }
            })
//            androidLogger()
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}