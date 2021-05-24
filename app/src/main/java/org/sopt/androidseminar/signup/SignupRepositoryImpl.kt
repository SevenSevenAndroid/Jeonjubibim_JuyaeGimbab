package org.sopt.androidseminar.signup

import io.reactivex.Single
import org.sopt.androidseminar.signup.RequestSignupData
import org.sopt.androidseminar.signup.ResponseSignupData
import org.sopt.androidseminar.signup.SignupDataSource
import org.sopt.androidseminar.signup.SignupRepository
import javax.inject.Inject

class SignupRepositoryImpl @Inject constructor(
    private val dataSource: SignupDataSource
) : SignupRepository{
    override fun signup(requestSignupData: RequestSignupData): Single<ResponseSignupData>
    = dataSource.signup(requestSignupData)
}