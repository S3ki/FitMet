package com.example.fitmet.screens

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
            elevation = CardDefaults.cardElevation(8.dp),
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Rekisteröidy", style = MaterialTheme.typography.headlineMedium)

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Sähköposti") },
                    isError = !isEmailValid,
                    modifier = Modifier.fillMaxWidth()
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
                    modifier = Modifier.fillMaxWidth()
                )
                if (!isPasswordValid) {
                    Text("Salasanan on oltava vähintään 6 merkkiä", color = MaterialTheme.colorScheme.error)
                }

                Button(
                    onClick = {
                        viewModel.registeredEmail.value = email
                        viewModel.registeredPassword.value = password
                        navController.navigate("profileSetup") {
                            popUpTo("register") { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = isEmailValid && isPasswordValid
                ) {
                    Text("Luo tili")
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
