package com.luu.android.data.remote

import com.luu.android.model.entity.Category
import com.luu.android.model.request.SigninRequest
import com.luu.android.model.request.SignupRequest
import com.luu.android.model.response.SigninResponse
import com.luu.android.model.response.SignupResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("auth/signup")
    suspend fun signup(@Body signupRequest: SignupRequest): SignupResponse

    @POST("auth/signin")
    suspend fun signin(@Body signinRequest: SigninRequest): SigninResponse

    @GET("categories")
    suspend fun fetchListCategory(): List<Category>
}