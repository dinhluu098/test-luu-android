package com.luu.android.model.response

import com.google.gson.annotations.SerializedName

data class SignupResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("firstName")
    val firstName: String? = null,
    @SerializedName("lastName")
    val lastName: String? = null,
    @SerializedName("role")
    val role: String? = null,
)