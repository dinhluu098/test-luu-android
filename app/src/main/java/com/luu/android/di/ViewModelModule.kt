package com.luu.android.di

import com.luu.android.ui.category.CategoryViewModel
import com.luu.android.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { CategoryViewModel(get(), get()) }
}

