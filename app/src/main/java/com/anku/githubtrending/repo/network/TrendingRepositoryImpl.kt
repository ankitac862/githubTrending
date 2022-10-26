package com.anku.githubtrending.repo.network

import android.content.Context
import com.anku.githubtrending.ITrendingRepository
import com.anku.githubtrending.model.Repo
import com.anku.githubtrending.model.RepositoryPojo
import com.anku.githubtrending.repo.db.ITrendingDao
import com.anku.githubtrending.utils.getCurrentTimeMillis
import com.anku.githubtrending.utils.isCacheExpired
import com.anku.githubtrending.utils.isNetworkAvailable

//This is repository class to handle whether to show data from API or to fetch from server
class TrendingRepositoryImpl(
    private val api: TrendingApi,
    private val dao: ITrendingDao,
    private val context: Context
) : ITrendingRepository {

    override suspend fun getTrendingRepositories(forceRefresh: Boolean , page : Int): List<Repo> {
        return try {
            val (lastRefresh, repos) = dao.getAllTrendingRepos()

            if (shouldFetchFromRemote(forceRefresh, lastRefresh, repos)) {
                fetchRepoFromApi(repos , page)
            } else {
                repos
            }

        } catch (e: Exception) {
            fetchRepoFromApi(emptyList() , 0)
        }

    }

    private fun shouldFetchFromRemote(
        forceRefresh: Boolean,
        lastRefresh: Long,
        repos: List<Repo>
    ) =
        isCacheExpired(lastRefresh)
                || repos.isNullOrEmpty()
                || forceRefresh

    private suspend fun fetchRepoFromApi(repos: List<Repo> , page : Int): List<Repo> {

        if (context.isNetworkAvailable() == false) {
            return repos
        }

        return try {
            val trendingRepositories = api.getTrendingRepositories().items


            if (trendingRepositories.isNullOrEmpty()) {
                repos
            } else {
                dao.deleteAll()
                dao.insertTrendingRepos(
                    RepositoryPojo(getCurrentTimeMillis(), trendingRepositories)
                )
                trendingRepositories
            }
        } catch (e: Exception) {
            repos
        }
    }

    companion object {
        const val CACHE_EXPIRY_HOURS = 2
    }
}