package com.example.fitmet.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class UserProfile(
    val name: String,
    val age: Int,
    val height: Int,
    val weight: Int,
    val gender: String,
    val fitnessGoal: String
)

class UserViewModel : ViewModel() {
    var registeredEmail = mutableStateOf("")
    var registeredPassword = mutableStateOf("")
    var isLoggedIn = mutableStateOf(false)
    var userProfile = mutableStateOf<UserProfile?>(null)
}