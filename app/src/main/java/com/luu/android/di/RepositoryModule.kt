package com.luu.android.di

import com.luu.android.repository.AppRepository
import com.luu.android.repository.UsersRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { UsersRepository(get()) }
    single { AppRepository(get()) }
}