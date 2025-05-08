package com.example.fitmet.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitmet.data.Achievement
import com.example.fitmet.data.FitDatabase
import com.example.fitmet.data.OfflineRepository
import com.example.fitmet.data.Steps
import com.example.fitmet.data.StepsRepository
import com.example.fitmet.data.User
import com.example.fitmet.models.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val offlineRepo = OfflineRepository(FitDatabase.getDatabase().userDao(), FitDatabase.getDatabase().achievementDao())
    private val stepsRepo = StepsRepository(FitDatabase.getDatabase().stepsDao())

    lateinit var userProfile: UserProfile
    private var currentUser : User? = null
    var registeredEmail = ""
    var registeredPassword = ""
    var isLoggedIn = false


    fun getStepForUser(userId : Int) : Flow<List<Steps>> {
       return stepsRepo.getAllStepsFromUser(userId)
    }

    fun getAchievementsForUser(userId: Int): Flow<List<Achievement>>{
        return offlineRepo.getAllAchievementsFromUser(userId)
    }

    fun login(username: String, password: String){
        viewModelScope.launch {
           val user = offlineRepo.login(username, password)
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

    fun logout(){
        offlineRepo.clearCurrentUserId()
    }

     fun registering(){
         currentUser = User(
             0, registeredEmail, offlineRepo.hashPassword(registeredPassword),
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

