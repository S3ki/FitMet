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
    var currentUser : User? = null
    var registeredEmail = ""
    var registeredPassword = ""
    var isLoggedIn = false


    fun login(){
        viewModelScope.launch {
           val user = offlineRepo.login(registeredEmail, registeredPassword)
            if (user != null) {
                isLoggedIn = true
                userProfile = UserProfile(
                    user.name,
                    user.age,
                    user.height,
                    user.weight,
                    user.gender,
                    user.fitnessGoal
                )
                currentUser = user
                // Tallentaa nykyisen käyttäjän id SharedPref
                offlineRepo.saveCurrentUserId(user.id)
            } else {
                Log.d("UserD", "Current user is null")
            }
        }
    }

     fun registering(){
         currentUser = User(
             0, registeredEmail.toString(), offlineRepo.hashPassword(registeredPassword.toString()),
             name = userProfile.name,
             age = userProfile.age,
             height = userProfile.height,
             weight = userProfile.weight,
             gender = userProfile.gender,
             fitnessGoal = userProfile.fitnessGoal,

             )
        viewModelScope.launch {
            Log.d("FDB", "Inserting the user into the DB")
            offlineRepo.insertUser(currentUser!!)
        }
    }



}

