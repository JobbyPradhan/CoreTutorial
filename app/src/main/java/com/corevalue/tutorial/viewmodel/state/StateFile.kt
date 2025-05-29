package com.corevalue.tutorial.viewmodel.state

import com.corevalue.tutorial.data.entity.Movie

data class MovieState(
    val movieList: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)