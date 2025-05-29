package com.corevalue.tutorial.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.corevalue.tutorial.data.entity.Movie
import com.corevalue.tutorial.data.local.dao.MovieDao

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
    companion object {
        @Volatile private var dbINSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (dbINSTANCE == null) {
                dbINSTANCE = Room.databaseBuilder<AppDatabase>(
                    context.applicationContext,AppDatabase::class.java, "CoreMovieDB"
                )/*.addMigrations(MIGRATION_18_19)*/
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return dbINSTANCE!!
        }
    }
}