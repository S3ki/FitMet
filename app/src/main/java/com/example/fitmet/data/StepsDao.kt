package com.example.fitmet.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StepsDao {

    @Insert
    suspend fun insertStep(step: Steps)

   @Query("SELECT * FROM steps WHERE userId = :userId ORDER BY date DESC")
     fun getStepsForUser(userId: Int): Flow<List<Steps>>
}
