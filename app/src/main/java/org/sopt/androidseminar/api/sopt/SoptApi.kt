package org.sopt.androidseminar.api.sopt

import org.sopt.androidseminar.signin.dto.RequestLoginData
import org.sopt.androidseminar.signin.dto.ResponseLoginData
import org.sopt.androidseminar.signup.RequestSignupData
import org.sopt.androidseminar.signup.ResponseSignupData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptApi {
    @POST("/login/signin")
    fun postLogin(
        @Body body : RequestLoginData
    ) : Call<ResponseLoginData>

    @POST ("login/signup")
    fun postSingup (
        @Body body : RequestSignupData
    ) : Call <ResponseSignupData>
}