package com.corevalue.tutorial.data.api

import com.corevalue.tutorial.data.entity.MovieResponse
import org.intellij.lang.annotations.Language
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("language") language: String = "en-US",
        @Query("page") page:Int = 1
    ): Response<MovieResponse>
}