package com.luu.android.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import jp.btechnologies.app.data.local.sharedpfers.SharedPrefKeys
import com.luu.android.data.local.sharedpfers.SharedPrefsWrapper
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localModule = module {
    single { providerSharedPrefs(androidApplication()) }
    single { providerSharedPrefsWrapper(get(), get()) }
}

fun providerSharedPrefs(app: Application): SharedPreferences {
    return app.getSharedPreferences(
        SharedPrefKeys.SHARED_PREFS_NAME, Context.MODE_PRIVATE
    )
}

fun providerSharedPrefsWrapper(
    sharedPreferences: SharedPreferences,
    gson: Gson
): SharedPrefsWrapper {
    return SharedPrefsWrapper(sharedPreferences, gson)
}
