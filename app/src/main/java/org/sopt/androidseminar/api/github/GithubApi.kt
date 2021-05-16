package org.sopt.androidseminar.api.github

import io.reactivex.Single
import org.sopt.androidseminar.home.dto.ResponseGithubRepository
import org.sopt.androidseminar.home.followers.ResponseGithubFollowers
import org.sopt.androidseminar.home.following.ResponseGithubFollowing
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users/{owner}/repos")
    fun getRepos(@Path("owner") owner :String) : Single<ResponseGithubRepository>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username : String) : Single<ResponseGithubFollowers>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String) : Single<ResponseGithubFollowing>

    

}