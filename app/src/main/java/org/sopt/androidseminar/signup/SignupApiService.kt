package org.sopt.androidseminar.signup

import io.reactivex.Single
import org.sopt.androidseminar.signin.dto.RequestLoginData
import org.sopt.androidseminar.signin.dto.ResponseLoginData
import org.sopt.androidseminar.signup.RequestSignupData
import org.sopt.androidseminar.signup.ResponseSignupData
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupApiService {
    @POST("login/signup")
    fun signup(@Body body : RequestSignupData) : Single<ResponseSignupData>
}