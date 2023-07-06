package com.luu.android

import android.app.Application
import com.luu.android.di.apiModule
import com.luu.android.di.localModule
import com.luu.android.di.repositoryModule
import com.luu.android.di.retrofitModule
import com.luu.android.di.viewModelModule
import com.luu.android.simpeldesa.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MainApp : Application() {
    companion object {
        lateinit var instance: MainApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MainApp)
            modules(
                listOf(
                    //databaseModule,
                    viewModelModule,
                    apiModule,
                    repositoryModule,
                    retrofitModule,
                    localModule
                )
            )

        }
        instance = this
    }
}