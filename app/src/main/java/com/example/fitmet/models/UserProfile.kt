package com.example.fitmet.models

import java.sql.Timestamp
import java.time.Instant
import java.util.Date

data class UserProfile(
    val name: String = "",
    val age: Int = 0,
    val height: Int = 0,       // in cm
    val weight: Int = 0,       // in kg
    val gender: String = "",   // "Male", "Female", "Other"
    val fitnessGoal: String = "", // e.g. "Weight Loss", "Muscle Gain"
    val lastUpdated: Date = Timestamp.from(Instant.now()), // Using Java Timestamp
    val distanceCovered: Int = 0 // Lisätty kenttä kuljetulle matkalle (kilometreinä)
) {
    val bmi: Double
        get() = calculateBmi()

    val bmiCategory: String
        get() = when {
            bmi == 0.0 -> "Not Calculated"
            bmi < 18.5 -> "Underweight"
            bmi < 25 -> "Normal"
            bmi < 30 -> "Overweight"
            else -> "Obese"
        }

    private fun calculateBmi(): Double {
        if (height == 0) return 0.0
        val heightInMeters = height / 100.0
        return weight / (heightInMeters * heightInMeters)
    }
}
