package com.luu.android.model.request

import com.google.gson.annotations.SerializedName

data class SigninRequest(
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("password")
    val password: String? = null,
)