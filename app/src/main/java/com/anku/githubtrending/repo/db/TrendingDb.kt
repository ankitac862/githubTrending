package com.anku.githubtrending.repo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anku.githubtrending.model.RepositoryPojo
/*
*  This is class to create database
* */
@Database(entities = [RepositoryPojo::class], version = 1, exportSchema = false)
@TypeConverters(TrendingConverter::class)
abstract class TrendingDb : RoomDatabase() {
    abstract fun trendingDao(): TrendingDBDao
}