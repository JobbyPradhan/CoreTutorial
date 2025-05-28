package com.corevalue.tutorial.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.themoviedb.org/3/" // Change this

    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NWNiZTMyZTM5YTMwNzhlNGYwNWMwNjM1NDYzMjdjNyIsIm5iZiI6MTc0ODQyNTEzMC4yMDg5OTk5LCJzdWIiOiI2ODM2ZDlhYWE5ZWEyMGFlZGYwMzdmNGQiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.mzpeLfRgyQhWvldesgKy3-933kf-gv_wJzte2tAm3ww"
    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(token))
        .addInterceptor(logger)
        .build()

    val api: MovieApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }
}
