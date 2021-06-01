package org.sopt.androidseminar.signup

import com.google.gson.annotations.SerializedName

data class ResponseSignupData(
    val success: Boolean,
    val message: String,
    val data : Data?
){
    data class Data(
        val message: String
    )
}