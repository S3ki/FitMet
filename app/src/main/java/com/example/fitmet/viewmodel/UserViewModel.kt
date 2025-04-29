package com.example.fitmet.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fitmet.models.UserProfile

class UserViewModel : ViewModel() {
    var userProfile = mutableStateOf<UserProfile?>(null)
    var registeredEmail = mutableStateOf("")
    var registeredPassword = mutableStateOf("")
    var isLoggedIn = mutableStateOf(false)

    // Lisätään matkaseuranta ja palkinnot
    var tokens = mutableStateOf(0)  // Tokens, jotka käyttäjä on ansainnut
    var distanceCovered = mutableStateOf(0)  // Käyttäjän kulkema matka (esim. askeleiden määrä km:nä)

    // Päivitä matka
    fun updateDistance(newDistance: Int) {
        distanceCovered.value = newDistance
        // Tarkistetaan, onko käyttäjä saavuttanut uusia tasoja
        checkRewards()
    }

    // Tarkistetaan, onko uusi palkinto ansaittu
    private fun checkRewards() {
        val levels = listOf(5, 10, 15, 20, 25)
        levels.forEach { level ->
            if (distanceCovered.value >= level && tokens.value < level * 10) {
                tokens.value += level * 10  // Palkinto, esim. 50 tokenia 5 km:stä
            }
        }
    }
}
