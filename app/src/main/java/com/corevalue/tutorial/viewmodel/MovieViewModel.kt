package com.corevalue.tutorial.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corevalue.tutorial.data.api.ApiResult
import com.corevalue.tutorial.data.entity.Movie
import com.corevalue.tutorial.data.repository.MovieRepository
import com.corevalue.tutorial.viewmodel.state.MovieState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository : MovieRepository) : ViewModel() {

    private val _movieStateFlow = MutableStateFlow(MovieState())
    val movieStateFlow : StateFlow<MovieState> = _movieStateFlow

    fun getNowPlayingMovie(){
        viewModelScope.launch {
            movieRepository.fetchMovies().collect { apiResult ->
                when(apiResult){
                    is ApiResult.Error -> {
                        _movieStateFlow.value = MovieState().copy(error = apiResult.message.toString())
                    }
                    is ApiResult.Loading -> {
                        _movieStateFlow.value = MovieState().copy(isLoading = true)
                    }
                    is ApiResult.Success -> {
                        _movieStateFlow.value = MovieState().copy(
                            movieList = apiResult.data?.results?:emptyList()
                        )

                    }
                }
            }
        }

    }




}