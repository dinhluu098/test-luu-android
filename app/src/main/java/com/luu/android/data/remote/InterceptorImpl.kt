package com.luu.android.data.remote

import androidx.annotation.NonNull
import com.luu.android.repository.UsersRepository
import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class InterceptorImpl(private val userRepository: UsersRepository) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(@NonNull chain: Interceptor.Chain): Response {

        val builder = initializeHeader(chain)
        val request = builder.build()

        return chain.proceed(request)
    }

    private fun initializeHeader(chain: Interceptor.Chain): Request.Builder {
        val originRequest = chain.request()
        val builder = originRequest.newBuilder()
            .header("Accept", "application/json")
            .addHeader("Language", "ja")
            .addHeader("Cache-Control", "no-cache")
            .addHeader("Cache-Control", "no-store")
            .method(originRequest.method(), originRequest.body())
        builder.addHeader(KEY_TOKEN, TOKEN_TYPE + userRepository.getToken())
        return builder
    }

    companion object {
        private const val TOKEN_TYPE = "Bearer "
        private const val KEY_TOKEN = "Authorization"
    }
}
