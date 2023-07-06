package com.luu.android.model.request

import com.google.gson.annotations.SerializedName

data class SignupRequest(
    @SerializedName("firstName")
    val firstName: String? = "Nguyen",
    @SerializedName("lastName")
    val lastName: String? = "Luu",
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("password")
    val password: String? = null
)