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
    val username: String,
    val password: String,
    val name: String,
    val age: Int = 0,
    val height: Int = 0,       // in cm
    val weight: Int = 0,       // in kg
    val gender: String = "",  // "Male", "Female", "Other"
    val fitnessGoal: String = "",
    // Maybe list of badges object here later
    // and some other info
){

}