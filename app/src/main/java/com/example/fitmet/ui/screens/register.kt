package com.example.fitmet.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitmet.viewmodel.UserViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight

@Composable
fun RegisterScreen(navController: NavController, viewModel: UserViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isEmailValid = remember(email) { email.contains("@") && email.contains(".") }
    val isPasswordValid = remember(password) { password.length >= 6 }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Rekisteröidy", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Sähköposti") },
                    isError = !isEmailValid,
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                )
                if (!isEmailValid) {
                    Text("Anna kelvollinen sähköposti", color = MaterialTheme.colorScheme.error)
                }

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Salasana") },
                    isError = !isPasswordValid,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                )
                if (!isPasswordValid) {
                    Text("Salasanan on oltava vähintään 6 merkkiä", color = MaterialTheme.colorScheme.error)
                }

                Button(
                    onClick = {
                        viewModel.registeredEmail = email
                        viewModel.registeredPassword = password
                        navController.navigate("profileSetup") {
                            popUpTo("register") { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = isEmailValid && isPasswordValid,
                    shape = MaterialTheme.shapes.large
                ) {
                    Text("Luo tili", fontWeight = FontWeight.Bold)
                }

                Text(
                    text = "Onko sinulla jo tili? Kirjaudu sisään",
                    modifier = Modifier
                        .clickable { navController.popBackStack() }
                        .padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
