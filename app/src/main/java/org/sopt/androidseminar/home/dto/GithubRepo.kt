package org.sopt.androidseminar.home.dto

import com.google.gson.annotations.SerializedName

data class GithubRepo(
    @SerializedName("name") val repository_name: String,
    @SerializedName("id") val repository_id: String,
    @SerializedName("created_at") val repository_date: String,
    @SerializedName("html_url") val repository_url: String
)