package com.example.fitmet.data

import android.content.Context
import kotlinx.coroutines.flow.Flow
import java.security.MessageDigest

/**
 * Tekija @Sakariye
 */
class OfflineRepository(private val userDao: UserDao) : UserRepository {

    private val prefs = FitApp.appContext.getSharedPreferences("fit_prefs", Context.MODE_PRIVATE)


    fun hashPassword(password: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) } // Hex string
    }

    suspend fun login(username: String, password: String): User? {
        val hashedPassword = hashPassword(password) // your hash function here
        return userDao.getUserForLogin(username, hashedPassword)

    }

    fun saveCurrentUserId(id: Int) = prefs.edit().putInt("current_user_id", id).apply()

    fun clearCurrentUserId() = prefs.edit().remove("current_user_id").apply()

    override fun getAllUsersStream(): Flow<List<User>> = userDao.getAllUsers()

    override fun getUserStream(id: Int): Flow<User?> {
        TODO("Not yet implemented")
    }

    override suspend fun insertUser(user: User) {
        userDao.insert(user)
    }

    override suspend fun deleteUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: User) {
        TODO("Not yet implemented")
    }
}