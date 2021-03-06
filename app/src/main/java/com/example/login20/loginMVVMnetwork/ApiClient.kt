package com.example.login20.loginMVVMnetwork

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    companion object {

        private val BASE_URL = "YOUR_URL_SERVER"
        private var retrofit: Retrofit? = null

        private val okHttpClientvalor = OkHttpClient.Builder()
            .connectTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

        fun  apiClient(): Retrofit {

            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .client(okHttpClientvalor)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }

    }
}