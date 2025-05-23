package com.corevalue.tutorial.data.entity

import com.corevalue.tutorial.R

data class Movie(
    val poster : Int,
    val title :String
)


//arrayListOf(R.drawable.bg_login_1,R.drawable.bg_login_2,R.drawable.bg_login_3,R.drawable.bg_login_4,R.drawable.bg_login)
val movie1 = Movie(R.drawable.bg_login_1,"Movie 1")
val movie2 = Movie(R.drawable.bg_login_2,"Movie 2")
val movie3 = Movie(R.drawable.bg_login_3,"Movie 3")
val movie4 = Movie(R.drawable.bg_login_4,"Movie 4")
val movie5 = Movie(R.drawable.bg_login,"Movie")


val newReleaseList = arrayListOf(movie1, movie2, movie3, movie4, movie5)
