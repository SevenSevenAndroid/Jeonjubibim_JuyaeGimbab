package org.sopt.androidseminar.signup

import org.sopt.androidseminar.signup.RequestSignupData
import org.sopt.androidseminar.signup.SignupApiService
import javax.inject.Inject

class SignupDataSource @Inject constructor(
    private val signupApiService : SignupApiService
) {
    fun signup(requestSignupData: RequestSignupData) = signupApiService.signup(requestSignupData)
}