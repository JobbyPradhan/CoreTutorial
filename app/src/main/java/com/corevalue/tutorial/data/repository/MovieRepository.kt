package com.corevalue.tutorial.data.repository

import com.corevalue.tutorial.data.api.MovieApi
import com.corevalue.tutorial.data.api.RetrofitInstance
import com.corevalue.tutorial.data.entity.MovieResponse

class MovieRepository {

    suspend fun fetchMovies(): MovieResponse? {
        val response = RetrofitInstance.api.getNowPlayingMovie()
        return if (response.isSuccessful) response.body() else null
    }
}