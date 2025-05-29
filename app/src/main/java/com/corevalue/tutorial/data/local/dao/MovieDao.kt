package com.corevalue.tutorial.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.corevalue.tutorial.data.entity.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieList: List<Movie>)

    @Query("SELECT * FROM Movie ORDER BY id ASC")
    fun getMovieList(): List<Movie>

    @Query("DELETE FROM Movie")
    suspend fun clearAll()
}