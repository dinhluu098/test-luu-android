package com.luu.android.model.response

import com.google.gson.annotations.SerializedName

data class SigninResponse(
    @SerializedName("user")
    val user: SignupResponse? = null,
    @SerializedName("accessToken")
    val accessToken: String? = null,
    @SerializedName("refreshToken")
    val refreshToken: String? = null,
)