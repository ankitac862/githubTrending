package com.anku.githubtrending.repo.db

import com.anku.githubtrending.model.RepositoryPojo

interface ITrendingDao {
    suspend fun getAllTrendingRepos(): RepositoryPojo

    suspend fun insertTrendingRepos(repos: RepositoryPojo)

    suspend fun deleteAll()
}