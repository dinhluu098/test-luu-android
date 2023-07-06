package com.luu.android.repository

import com.luu.android.model.request.SignupRequest
import com.luu.android.data.remote.ApiService
import com.luu.android.model.request.SigninRequest

class AppRepository(
    private val apiService: ApiService
) {
    suspend fun signup(signupRequest: SignupRequest) = apiService.signup(signupRequest)
    suspend fun signin(signinRequest: SigninRequest) = apiService.signin(signinRequest)
    suspend fun fetchListCategory() = apiService.fetchListCategory()
}