package com.example.pizzadel.api

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(HEADER_API_KEY_NAME, HEADER_API_KEY)
            .addHeader(HEADER_API_HOST_NAME, HEADER_API_HOST)
            .build()

        return chain.proceed(request)
    }

    companion object {
        private val HEADER_API_KEY_NAME = "X-RapidAPI-Key"
        private val HEADER_API_KEY = "75b6c53446mshae6ce9b65f579d8p101810jsn7e29e5df6b4f"
        private val HEADER_API_HOST_NAME = "X-RapidAPI-Host"
        private val HEADER_API_HOST = "pizza-and-desserts.p.rapidapi.com"
    }
}