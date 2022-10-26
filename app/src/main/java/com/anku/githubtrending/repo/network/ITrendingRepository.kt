package com.anku.githubtrending

import com.anku.githubtrending.model.Repo

interface ITrendingRepository {
    suspend fun getTrendingRepositories(forceRefresh:Boolean = false , pos : Int): List<Repo>
}