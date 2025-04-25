package com.example.fitmet.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitmet.models.UserProfile
import com.example.fitmet.viewmodel.UserViewModel


@Composable
fun ProfileSetupScreen(navController: NavController, viewModel: UserViewModel) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("") }
    var fitnessGoal by remember { mutableStateOf("") }

    val genderOptions = listOf("Male", "Female", "Other")
    val fitnessGoals = listOf("Weight Loss", "Muscle Gain", "Maintenance", "Endurance", "Flexibility")

    val isNameValid = remember(name) { name.matches(Regex("^[A-Za-z ]+$")) }
    val isAgeValid = remember(age) { age.all { it.isDigit() } && age.toIntOrNull() != null && age.toInt() in 18..100 }
    val isHeightValid = remember(height) { height.all { it.isDigit() } && height.toIntOrNull() != null }
    val isWeightValid = remember(weight) { weight.all { it.isDigit() } && weight.toIntOrNull() != null }
    val isFormValid = name.isNotBlank() && isNameValid && isAgeValid && isHeightValid && isWeightValid && selectedGender.isNotBlank() && fitnessGoal.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Profile Setup",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Complete your profile to get personalized recommendations",
            style = MaterialTheme.typography.bodyMedium
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            isError = !isNameValid,
            modifier = Modifier.fillMaxWidth()
        )
        if (!isNameValid) {
            Text("Name cannot contain numbers", color = MaterialTheme.colorScheme.error)
        }

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedTextField(
                value = age,
                onValueChange = { if (it.all { char -> char.isDigit() }) age = it },
                label = { Text("Age") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                isError = !isAgeValid,
                modifier = Modifier.weight(1f)
            )

            OutlinedTextField(
                value = height,
                onValueChange = { if (it.all { char -> char.isDigit() }) height = it },
                label = { Text("Height (cm)") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                isError = !isHeightValid,
                modifier = Modifier.weight(1f)
            )

            OutlinedTextField(
                value = weight,
                onValueChange = { if (it.all { char -> char.isDigit() }) weight = it },
                label = { Text("Weight (kg)") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                isError = !isWeightValid,
                modifier = Modifier.weight(1f)
            )
        }

        Text(
            text = "Gender",
            style = MaterialTheme.typography.labelLarge
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            genderOptions.forEach { gender ->
                FilterChip(
                    selected = selectedGender == gender,
                    onClick = { selectedGender = gender },
                    modifier = Modifier.padding(4.dp),
                    label = { Text(gender) }
                )
            }
        }

        Text(
            text = "Fitness Goal",
            style = MaterialTheme.typography.labelLarge
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(150.dp)
        ) {
            items(fitnessGoals) { goal ->
                FilterChip(
                    selected = fitnessGoal == goal,
                    onClick = { fitnessGoal = goal },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(goal) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.userProfile.value = UserProfile(
                    name = name,
                    age = age.toIntOrNull() ?: 0,
                    height = height.toIntOrNull() ?: 0,
                    weight = weight.toIntOrNull() ?: 0,
                    gender = selectedGender,
                    fitnessGoal = fitnessGoal
                )
                navController.navigate("Homemain") {
                    popUpTo("profileSetup") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isFormValid
        ) {
            Text("Save Profile")
        }
    }
}
