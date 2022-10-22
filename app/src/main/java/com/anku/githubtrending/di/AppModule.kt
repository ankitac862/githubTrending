package com.anku.githubtrending.di

import com.anku.githubtrending.ITrendingRepository
import com.anku.githubtrending.repo.network.TrendingRepositoryImpl
import org.koin.dsl.module
import com.anku.githubtrending.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    single<ITrendingRepository> { TrendingRepositoryImpl(get(), get(), get()) }
    viewModel { MainViewModel(get()) }
}