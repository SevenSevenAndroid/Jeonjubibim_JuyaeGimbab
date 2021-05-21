package org.sopt.androidseminar.login

import org.sopt.androidseminar.login.LoginApiService
import org.sopt.androidseminar.signin.dto.RequestLoginData
import javax.inject.Inject

class LoginDataSource @Inject constructor(
    private val loginApiService: LoginApiService
) {
    fun login(requestLoginData: RequestLoginData) = loginApiService.login(requestLoginData)
}