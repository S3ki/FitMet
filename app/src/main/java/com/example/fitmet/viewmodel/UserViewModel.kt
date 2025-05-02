package com.example.fitmet.viewmodel

import android.util.Log
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
    val allUsers = offlineRepo.getAllUsersStream()
    lateinit var userProfile: UserProfile
    var registeredEmail = ""
    var registeredPassword = ""
    var isLoggedIn = mutableStateOf(false)

     fun registering(){
        viewModelScope.launch {
            Log.d("FDB", "Inserting the user into the DB")
            offlineRepo.insertUser(User(
                0, registeredEmail.toString(), registeredPassword.toString(),
                name = userProfile.name,
                age = userProfile.age,
                height = userProfile.height,
                weight = userProfile.weight,
                gender = userProfile.gender,
                fitnessGoal = userProfile.fitnessGoal,

            ))
        }
    }
}

