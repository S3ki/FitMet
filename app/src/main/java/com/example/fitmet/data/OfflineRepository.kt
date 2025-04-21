package com.example.fitmet.data

import kotlinx.coroutines.flow.Flow

/**
 * Tekija @Sakariye
 */
class OfflineRepository : UserRepository {
    override fun getAllUsersStream(): Flow<List<User>> {
        TODO("Not yet implemented")
    }

    override fun getUserStream(id: Int): Flow<User?> {
        TODO("Not yet implemented")
    }

    override suspend fun insertUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: User) {
        TODO("Not yet implemented")
    }
}