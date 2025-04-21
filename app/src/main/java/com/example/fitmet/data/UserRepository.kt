package com.example.fitmet.data

import kotlinx.coroutines.flow.Flow

/**
 * Tekija @Sakariye
 */
interface UserRepository {

    fun getAllUsersStream(): Flow<List<User>>


    fun getUserStream(id: Int): Flow<User?>


    suspend fun insertUser(user: User)


    suspend fun deleteUser(user: User)


    suspend fun updateUser(user: User)
}