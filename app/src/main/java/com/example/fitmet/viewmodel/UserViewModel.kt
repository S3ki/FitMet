package com.example.fitmet.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    var registeredEmail = mutableStateOf("")
    var registeredPassword = mutableStateOf("")
    var isLoggedIn = mutableStateOf(false)
}
