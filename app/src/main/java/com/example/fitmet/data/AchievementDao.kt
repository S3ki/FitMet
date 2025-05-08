package com.example.fitmet.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AchievementDao {

    @Insert
    suspend fun insertAchievement(achievement: Achievement)

    @Query("SELECT * FROM achievement WHERE userId = :userId ORDER BY level ASC")
    fun getStepsForUser(userId: Int): Flow<List<Achievement>>
}