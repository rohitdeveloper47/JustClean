package com.dashboard.justclean.di.module

import com.dashboard.justclean.data.repository.UserRepository
import org.koin.dsl.module


val RepositoryModule = module {
    single {
        UserRepository(get())
    }
}