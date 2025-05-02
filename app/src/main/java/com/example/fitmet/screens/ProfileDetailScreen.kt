package com.example.fitmet.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitmet.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDetailScreen(navController: NavController, viewModel: UserViewModel) {
    val userProfile = viewModel.userProfile

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User Profile") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (userProfile != null) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(24.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Name: ${userProfile.name}", fontWeight = FontWeight.Bold)
                Text("Age: ${userProfile.age}")
                Text("Gender: ${userProfile.gender}")
                Text("Height: ${userProfile.height} cm")
                Text("Weight: ${userProfile.weight} kg")
                Text("Fitness Goal: ${userProfile.fitnessGoal}")
                Text("BMI: %.2f".format(userProfile.bmi))
                Text("BMI Category: ${userProfile.bmiCategory}")

                Spacer(modifier = Modifier.height(32.dp))

                // Selkeä painike takaisin etusivulle
                Button(
                    onClick = { navController.navigate("Homemain") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Takaisin etusivulle")
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No user profile found.")
            }
        }
    }
}
