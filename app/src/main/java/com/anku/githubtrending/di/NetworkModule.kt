package com.anku.githubtrending.di

import com.anku.githubtrending.repo.network.TrendingApi.Companion.BASE_URL
import com.anku.githubtrending.BuildConfig
import com.anku.githubtrending.repo.network.TrendingApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
        val okHttpClient = builder.build()
        okHttpClient
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(TrendingApi::class.java)
        api
    }
}