package com.corevalue.tutorial.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corevalue.tutorial.data.entity.Movie
import com.corevalue.tutorial.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel() : ViewModel() {

    private val movieRepository = MovieRepository()
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies


    fun getNowPlayingMovie() {
        viewModelScope.launch{
            val result = movieRepository.fetchMovies()
            result?.let {
                _movies.value = it.results
            }
        }

    }




}