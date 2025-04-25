package com.example.fitmet.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fitmet.models.UserProfile

class UserViewModel : ViewModel() {
    var userProfile = mutableStateOf<UserProfile?>(null)
    var registeredEmail = mutableStateOf("")
    var registeredPassword = mutableStateOf("")
    var isLoggedIn = mutableStateOf(false)

}


class UserDetails(){

}