package com.anku.githubtrending.repo.network

import com.anku.githubtrending.model.Repo
import com.anku.githubtrending.model.RepositoryPojo
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendingApi {
    companion object {
        //const val BASE_URL = "https://gh-trending-api.herokuapp.com/"  // Have the change url becz API stop working
        const val BASE_URL = "https://api.github.com/"
    }

//    @GET("/repositories?")
//    suspend  fun getTrendingRepositories(@Query("since") since: String): List<Repo>

    @GET("search/repositories?q=sort=stars")
    suspend fun getTrendingRepositories(): RepositoryPojo

}