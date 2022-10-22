package com.anku.githubtrending.repo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anku.githubtrending.model.Repo
import com.anku.githubtrending.model.RepositoryPojo
import io.reactivex.Single

@Dao
interface TrendingDBDao {

    @Query("SELECT * FROM repo")
    suspend fun getAllTrendingRepos(): RepositoryPojo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repos: RepositoryPojo)

    @Query("DELETE FROM repo")
    suspend fun deleteAll()
}