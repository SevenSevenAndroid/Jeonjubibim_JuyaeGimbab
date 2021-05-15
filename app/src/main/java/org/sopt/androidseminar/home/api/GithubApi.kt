package org.sopt.androidseminar.home.api

import io.reactivex.Single
import org.sopt.androidseminar.home.dto.RepositoryResponseModelItem
import org.sopt.androidseminar.home.dto.ResponseGithubRepository
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users/{owner}/repos")
    fun getRepos(@Path("owner") owner :String) : Single<ResponseGithubRepository>
}