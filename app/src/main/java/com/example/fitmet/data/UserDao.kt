package com.example.fitmet.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow



@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)


    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * from user WHERE id = :id")
    fun getUser(id: Int): Flow<User>

    @Query("SELECT * from user")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE username = :username AND password = :password LIMIT 1")
    suspend fun getUserForLogin(username: String, password: String): User?

}