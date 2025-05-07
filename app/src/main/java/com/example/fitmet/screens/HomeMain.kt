package com.example.fitmet.screens

import StepCounter
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitmet.viewmodel.UserViewModel
import com.example.fitmet.viewmodel.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMain(navController: NavController, viewModel: UserViewModel, themeViewModel: ThemeViewModel) {
    val user = viewModel.userProfile

    val context = LocalContext.current
    var steps by remember { mutableStateOf<Long?>(null) }



    LaunchedEffect(Unit) {
        val counter = StepCounter(context)
        steps = counter.steps()
    }


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
                        viewModel.isLoggedIn = false
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
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Text(
                text = "Päivän Yhteenveto",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            // UI Kortit

            StatCard("🔥 Kalorit", "1800 kcal", )

            StatCard("👟 Askeleet", (steps ?: 0).toString(), onClick = {
                navController.navigate("steps")
            })

            StatCard("⏱️ Saavutukset", "10 kpl", onClick = {
                navController.navigate("achievements")
            })

            Button(onClick = {
                viewModel.registering()
            }) { Text("DB test") }
        }
    }
}

@Composable
fun StatCard(title: String, value: String, onClick: (() -> Unit)? = null) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()
    val elevation = animateDpAsState(targetValue = if (isPressed.value) 2.dp else 8.dp)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,  // Ei oletusanimaatiota, vaan meidän oma
                enabled = onClick != null
            ) { onClick?.invoke() },
        colors = CardDefaults.cardColors(
            containerColor = if (isPressed.value) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            else MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation.value),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
