package com.dashboard.justclean.di.module

import android.content.Context
import com.dashboard.justclean.data.NetworkHelper
import com.dashboard.justclean.data.URLFactory
import com.dashboard.justclean.data.datasource.DataSource
import com.dashboard.justclean.data.datasource.DataSourceImp
import com.dashboard.justclean.data.service.UserService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val ApplicationModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), URLFactory.BASE_URL) }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }
    single<DataSource> {
        return@single DataSourceImp(get())
    }
}

    private fun provideNetworkHelper(context: Context) = NetworkHelper(context)
    private fun provideOkHttpClient() = if (URLFactory.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    private fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    private fun provideApiService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)
