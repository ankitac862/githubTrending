package com.anku.githubtrending.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BuiltBy(
    val avatar: String,
    val url: String,
    val username: String
) : Parcelable