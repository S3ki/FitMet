package com.example.fitmet.data

/**
 * Tekija @Sakariye
 */
class StepsRepository(private val stepsDao: StepsDao) {

    suspend fun insertSteps(steps: Steps) {
        stepsDao.insertStep(steps)
    }
   /* suspend fun getAllStepsFromUser(user: User){
        stepsDao.getStepsForUser(user.id)
    } */
}