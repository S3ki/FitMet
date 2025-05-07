package com.example.fitmet.data

import kotlinx.coroutines.flow.Flow

/**
 * Tekija @Sakariye
 */
class StepsRepository(private val stepsDao: StepsDao) {

    suspend fun insertSteps(steps: Steps) {
        stepsDao.insertStep(steps)
    }

     fun getAllStepsFromUser(user: User) : Flow<List<Steps>> {
        return stepsDao.getStepsForUser(user.id)
    }
}