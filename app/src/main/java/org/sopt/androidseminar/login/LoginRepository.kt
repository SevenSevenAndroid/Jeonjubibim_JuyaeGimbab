package org.sopt.androidseminar.login

import io.reactivex.Single
import org.sopt.androidseminar.signin.dto.RequestLoginData
import org.sopt.androidseminar.signin.dto.ResponseLoginData

interface LoginRepository {
    fun login(requestLoginData: RequestLoginData) : Single<ResponseLoginData>
}