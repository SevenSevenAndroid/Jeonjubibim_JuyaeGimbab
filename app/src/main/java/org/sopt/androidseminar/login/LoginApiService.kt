package org.sopt.androidseminar.login

import io.reactivex.Single
import org.sopt.androidseminar.signin.dto.RequestLoginData
import org.sopt.androidseminar.signin.dto.ResponseLoginData
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST("login/signin")
    fun login(@Body body : RequestLoginData) :Single<ResponseLoginData>
}