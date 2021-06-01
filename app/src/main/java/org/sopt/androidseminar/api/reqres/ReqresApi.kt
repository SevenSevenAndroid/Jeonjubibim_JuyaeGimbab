package org.sopt.androidseminar.api.reqres

import io.reactivex.Single
import org.sopt.androidseminar.api.reqres.dto.BaseUser
import org.sopt.androidseminar.api.reqres.dto.RequestReqresUsers
import org.sopt.androidseminar.api.reqres.dto.ResponseReqresUsers
import retrofit2.Call
import retrofit2.http.*

interface ReqresApi {
    @GET("/api/users")
    fun getUsersList(@Query("page") page: Int) : Single<BaseUser>
}