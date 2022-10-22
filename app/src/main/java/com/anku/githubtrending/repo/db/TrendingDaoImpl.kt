package com.anku.githubtrending.repo.db

import com.anku.githubtrending.model.RepositoryPojo

class TrendingDaoImpl(private val dao: TrendingDBDao) : ITrendingDao {
    override suspend fun getAllTrendingRepos(): RepositoryPojo = dao.getAllTrendingRepos()

    override suspend fun insertTrendingRepos(repos: RepositoryPojo) = dao.insert(repos)

    override suspend fun deleteAll() = dao.deleteAll()
}