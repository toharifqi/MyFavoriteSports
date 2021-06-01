package com.toharifqi.myfavoritesports

import android.app.Application
import com.toharifqi.myfavoritesports.core.di.databaseModule
import com.toharifqi.myfavoritesports.core.di.networkModule
import com.toharifqi.myfavoritesports.core.di.repositoryModule
import com.toharifqi.myfavoritesports.di.useCaseModule
import com.toharifqi.myfavoritesports.di.viewModeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModeModule
                )
            )
        }
    }
}