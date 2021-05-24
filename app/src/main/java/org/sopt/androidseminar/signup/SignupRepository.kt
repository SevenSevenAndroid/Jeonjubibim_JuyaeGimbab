package org.sopt.androidseminar.signup

import io.reactivex.Single

interface SignupRepository {
    fun signup(requestSignupData: RequestSignupData) : Single<ResponseSignupData>
}