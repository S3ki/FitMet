package com.example.fitmet.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Tekija @Sakariye
 */

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val password: String,
    // Maybe list of badges object here later
    // and some other info
){

}