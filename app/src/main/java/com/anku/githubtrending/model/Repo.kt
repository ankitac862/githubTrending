package com.anku.githubtrending.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repo(
    val builtBy: Array<BuiltBy>,
    val description: String?,
    val forks: Number,
    val language: String?,
    val languageColor: String?,
    val rank: Number,
    val repositoryName: String,
    val since: String,
    val starsSince: Number,
    val totalStars: Number,
    val url: String,
    val username: String,
    var checked: Boolean = false
) : Parcelable