package com.corevalue.tutorial.data.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

data class MovieResponse(
    val dates: Dates?=null,
    val page: Int?= null,
    val results: List<Movie>
)

data class Dates(
    val maximum: String,
    val minimum: String
)

@Entity
data class Movie(
    val adult: Boolean,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    @PrimaryKey
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
