package com.anku.githubtrending.model

import androidx.room.ColumnInfo
import androidx.room.Entity
// Mock repository class for providing data
@Entity(tableName = "repo", primaryKeys = ["lastRefresh"])
data class RepositoryPojo(
    @ColumnInfo( name = "lastRefresh") val lastRefresh: Long,
    @ColumnInfo( name = "repos") val repos: List<Repo>
    )