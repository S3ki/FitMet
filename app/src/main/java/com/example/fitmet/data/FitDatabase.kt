package com.example.fitmet.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Tekija @Sakariye
 */

@Database(entities = [User::class, Steps::class], version = 3, exportSchema = false)
abstract class FitDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun stepsDao(): StepsDao

    companion object {

        @Volatile
        private var Instance: FitDatabase? = null

        fun getDatabase(): FitDatabase {
            // if the nstance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(FitApp.appContext, FitDatabase::class.java, "user_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}