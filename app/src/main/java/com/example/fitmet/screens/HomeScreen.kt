package com.example.fitmet.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitmet.viewmodel.UserViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: UserViewModel) {
    val userProfile = viewModel.userProfile.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Tervetuloa Fit Met:iin!",
                style = MaterialTheme.typography.headlineMedium
            )

            if (userProfile != null) {
                Spacer(modifier = Modifier.height(24.dp))
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Your Profile",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text("Name: ${userProfile.name}")
                        Text("Age: ${userProfile.age}")
                        Text("Height: ${userProfile.height} cm")
                        Text("Weight: ${userProfile.weight} kg")
                        Text("Gender: ${userProfile.gender}")
                        Text("Fitness Goal: ${userProfile.fitnessGoal}")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.isLoggedIn.value = false
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            ) {
                Text("Kirjaudu ulos")
            }
        }
    }
}
