package com.anku.githubtrending.di

import androidx.room.Room
import com.anku.githubtrending.repo.db.ITrendingDao
import com.anku.githubtrending.repo.db.TrendingDaoImpl
import com.anku.githubtrending.repo.db.TrendingDb
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(get(), TrendingDb::class.java, "trending.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single<ITrendingDao> { TrendingDaoImpl((get() as TrendingDb).trendingDao()) }
}