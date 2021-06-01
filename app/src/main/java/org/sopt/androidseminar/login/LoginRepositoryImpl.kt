package org.sopt.androidseminar.login

import io.reactivex.Single
import org.sopt.androidseminar.signin.dto.RequestLoginData
import org.sopt.androidseminar.signin.dto.ResponseLoginData
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataSource: LoginDataSource
): LoginRepository {
    override fun login(requestLoginData: RequestLoginData): Single<ResponseLoginData>
    = dataSource.login(requestLoginData)
}