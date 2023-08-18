package com.footballproject.data.remote.interceptor

import com.footballproject.core.Constants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request: Request = original.newBuilder()
            .header("X-Auth-Token", Constants.API_KEY)
            .method(original.method, original.body)
            .build()

        return chain.proceed(request)
    }
}
