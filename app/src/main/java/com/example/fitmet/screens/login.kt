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

@Composable
fun LoginScreen(navController: NavController, viewModel: UserViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (email == viewModel.registeredEmail.value &&
                    password == viewModel.registeredPassword.value
                ) {
                    viewModel.isLoggedIn.value = true
                    navController.navigate("Homemain") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Don't have an account? Register",
            modifier = Modifier
                .clickable { navController.navigate("register") }
                .padding(top = 8.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}