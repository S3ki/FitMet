package com.example.fitmet.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StepsDao {

    @Insert
    suspend fun insertStep(step: Steps)

  //  @Query("SELECT * FROM steps WHERE userId = :userId ORDER BY date DESC")
  //  suspend fun getStepsForUser(userId: Int): List<Steps>
}
