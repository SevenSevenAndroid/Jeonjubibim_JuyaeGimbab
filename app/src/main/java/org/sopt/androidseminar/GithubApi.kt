package org.sopt.androidseminar

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users/{owner}/repos")
    fun getRepos(@Path("owner") owner :String) : Single<List<RepositoryResponseModelItem>>
}