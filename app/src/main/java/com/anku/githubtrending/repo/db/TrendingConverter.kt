package com.anku.githubtrending.repo.db

import androidx.room.TypeConverter
import com.anku.githubtrending.model.BuiltBy
import com.anku.githubtrending.model.Repo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class TrendingConverter {

    @TypeConverter
    fun fromStringToContributorDto(value: String?): List<BuiltBy> {
        val listType =
            object : TypeToken<ArrayList<BuiltBy>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromContributorDtoToString(list: List<BuiltBy>): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringToRepositoryDto(value: String?): List<Repo> {
        val listType =
            object : TypeToken<ArrayList<Repo>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromRepositoryDtoToString(list: List<Repo>): String? {
        return Gson().toJson(list)
    }
}