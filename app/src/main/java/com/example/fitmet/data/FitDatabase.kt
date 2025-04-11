package com.example.fitmet.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Tekija @Sakariye
 */

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class FitDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var Instance: FitDatabase? = null

        fun getDatabase(context: Context): FitDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FitDatabase::class.java, "user_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}