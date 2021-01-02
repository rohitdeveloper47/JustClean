package com.dashboard.justclean.di.module

import com.dashboard.justclean.ui.viewmodel.CategoryViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel {
        CategoryViewModel(get(),get())
    }
}