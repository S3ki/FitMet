package com.example.fitmet.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitmet.viewmodel.UserViewModel
import com.example.fitmet.viewmodel.ThemeViewModel  // Lisätty ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMain(navController: NavController, viewModel: UserViewModel, themeViewModel: ThemeViewModel) {
    val user = viewModel.userProfile.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Hei, ${user?.name ?: "Käyttäjä"} 👋",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("profileDetail")
                    }) {
                        Icon(Icons.Default.Person, contentDescription = "Profiili")
                    }

                    // Teeman vaihtaminen
                    IconButton(onClick = {
                        themeViewModel.isDarkMode.value = !themeViewModel.isDarkMode.value
                    }) {
                        Icon(
                            imageVector = if (themeViewModel.isDarkMode.value) Icons.Default.LightMode else Icons.Default.DarkMode,
                            contentDescription = "Vaihda teema"
                        )
                    }

                    IconButton(onClick = {
                        viewModel.isLoggedIn.value = false
                        navController.navigate("login") {
                            popUpTo("Homemain") { inclusive = true }
                        }
                    }) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Uloskirjautuminen")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Päivän Yhteenveto",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            StatCard("🔥 Kalorit", "1800 kcal")
            StatCard("👟 Askeleet", "7500")
            StatCard("⏱️ Aktiivinen Aika", "45 min")
        }
    }
}

@Composable
fun StatCard(title: String, value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(value, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        }
    }
}
