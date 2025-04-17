package com.example.fitmet.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitmet.viewmodel.UserViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: UserViewModel) {
    val userProfile = viewModel.userProfile.value
    var showProfileDropdown by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {

        // Centered Welcome and Logout
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Tervetuloa Fit Met:iin!",
                style = MaterialTheme.typography.headlineMedium
            )

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

        // Top-left profile icon with dropdown
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            IconButton(onClick = { showProfileDropdown = true }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            DropdownMenu(
                expanded = showProfileDropdown,
                onDismissRequest = { showProfileDropdown = false },
                modifier = Modifier.width(IntrinsicSize.Max)
            ) {
                if (userProfile != null) {
                    DropdownMenuItem(
                        text = { Text("View Profile", fontWeight = FontWeight.Medium) },
                        onClick = {
                            showProfileDropdown = false
                            navController.navigate("profileDetail")
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Edit Profile") },
                        onClick = {
                            showProfileDropdown = false
                            navController.navigate("profileSetup")
                        }
                    )
                } else {
                    DropdownMenuItem(
                        text = { Text("Create Profile") },
                        onClick = {
                            showProfileDropdown = false
                            navController.navigate("profileSetup")
                        }
                    )
                }
            }
        }
    }
}
