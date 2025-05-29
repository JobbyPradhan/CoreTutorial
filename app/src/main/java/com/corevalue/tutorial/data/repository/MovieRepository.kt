package com.corevalue.tutorial.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.corevalue.tutorial.data.entity.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.corevalue.tutorial.data.api.ApiResult
import com.corevalue.tutorial.data.api.ErrorHandler
import com.corevalue.tutorial.data.api.RetrofitInstance
import com.corevalue.tutorial.data.local.dao.MovieDao
import retrofit2.HttpException

class MovieRepository(
    private val movieDao: MovieDao,
    private val connectivityManager: ConnectivityManager
) {

    fun fetchMovies(): Flow<ApiResult<MovieResponse>> = flow {
        try {
            emit(ApiResult.Loading())

            // First emit cached data if available
            val cachedMovies = movieDao.getMovieList()
            if (cachedMovies.isNotEmpty()) {
                emit(ApiResult.Success(MovieResponse(results = cachedMovies)))
            }

            // Only fetch from network if there's internet connection
            if (isNetworkAvailable()) {
                val response = RetrofitInstance.api.getNowPlayingMovie()
                // Clear old data and insert new
                movieDao.clearAll()
                movieDao.insert(response.results)
                emit(ApiResult.Success(response))
            }
        } catch (e: HttpException) {
            emit(ApiResult.Error(ErrorHandler.apiError(e.response())))
            // If we have cached data and network fails, emit cached data with a flag
            val cachedMovies = movieDao.getMovieList()
            if (cachedMovies.isNotEmpty()) {
                emit(ApiResult.Success(
                    MovieResponse(results = cachedMovies),
                    isFromCache = true
                ))
            }
        } catch (e: Exception) {
            emit(ApiResult.Error(ErrorHandler.networkError(e.message.toString())!!, 1))
            // If we have cached data and network fails, emit cached data with a flag
            val cachedMovies = movieDao.getMovieList()
            if (cachedMovies.isNotEmpty()) {
                emit(ApiResult.Success(
                    MovieResponse(results = cachedMovies),
                    isFromCache = true
                ))
            }
        }
    }

    @SuppressLint("MissingPermission") // Don't forget to add network permission in manifest
    private fun isNetworkAvailable(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }
}