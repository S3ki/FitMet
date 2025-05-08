package com.example.fitmet.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Tekija @Sakariye
 */

@Database(entities = [User::class, Steps::class, Achievement::class], version = 6, exportSchema = false)
abstract class FitDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun stepsDao(): StepsDao
    abstract fun achievementDao(): AchievementDao

    companion object {

        @Volatile
        private var Instance: FitDatabase? = null

        fun getDatabase(): FitDatabase {
            // if the instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(FitApp.appContext, FitDatabase::class.java, "user_database")
//                    .fallbackToDestructiveMigration(true)
                    .build()

                    .also { Instance = it }
            }
        }
    }
}