package com.ks.myanimelist

import android.app.Application
import com.ks.myanimelist.core.di.databaseModule
import com.ks.myanimelist.core.di.networkModule
import com.ks.myanimelist.core.di.repositoryModule
import com.ks.myanimelist.di.useCaseModule
import com.ks.myanimelist.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    com.ks.myanimelist.core.di.databaseModule,
                    com.ks.myanimelist.core.di.networkModule,
                    com.ks.myanimelist.core.di.repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}