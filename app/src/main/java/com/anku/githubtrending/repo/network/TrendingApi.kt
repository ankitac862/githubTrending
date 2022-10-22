package com.anku.githubtrending.repo.network

import com.anku.githubtrending.model.Repo
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendingApi {
    companion object {
        const val BASE_URL = "https://gh-trending-api.herokuapp.com"
    }

    @GET("/repositories?")
    suspend  fun getTrendingRepositories(@Query("since") since: String): List<Repo>

}