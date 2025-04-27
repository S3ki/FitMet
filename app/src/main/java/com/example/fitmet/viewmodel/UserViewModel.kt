package com.example.fitmet.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitmet.data.FitDatabase
import com.example.fitmet.data.OfflineRepository
import com.example.fitmet.data.User
import com.example.fitmet.models.UserProfile
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val offlineRepo = OfflineRepository(FitDatabase.getDatabase().userDao())

    var userProfile = mutableStateOf<UserProfile?>(null)
    var registeredEmail = mutableStateOf("")
    var registeredPassword = mutableStateOf("")
    var isLoggedIn = mutableStateOf(false)

     fun registering(){
        viewModelScope.launch {
            offlineRepo.insertUser(User(0,registeredEmail.toString(), registeredPassword.toString()))
        }
    }
}

