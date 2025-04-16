package com.example.fitmet.models
import com.google.firebase.Timestamp
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserProfile(
    val userId: String = "",
    val name: String = "",
    val age: Int = 0,
    val height: Int = 0,       // in cm
    val weight: Int = 0,       // in kg
    val gender: String = "",   // "Male", "Female", "Other"
    val fitnessGoal: String = "", // e.g. "Weight Loss", "Muscle Gain"
    val lastUpdated: Timestamp = Timestamp.now(),

) {
    private fun calculateBmi(): Double {
        if (height == 0) return 0.0
        val heightInMeters = height / 100.0
        return weight / (heightInMeters * heightInMeters)
    }
}

