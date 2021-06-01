package org.sopt.androidseminar.signup

data class RequestSignupData(
    val email: String,
    val password: String,
    val sex : String,
    val nickname: String,
    val phone : String,
    val birth: String
)