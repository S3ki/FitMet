package com.example.fitmet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.fitmet.screens.*
import com.example.fitmet.viewmodel.UserViewModel
import com.example.fitmet.ui.theme.FitMetTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            FitMetTheme {
                FitmetApp()
            }
        }
    }
}

@Composable
fun FitmetApp() {
    val navController: NavHostController = rememberNavController()
    val viewModel: UserViewModel = viewModel()

    Surface(color = MaterialTheme.colorScheme.background) {
        NavHost(navController = navController, startDestination = "login") {
            composable("login") { LoginScreen(navController, viewModel) }
            composable("register") { RegisterScreen(navController, viewModel) }
            composable("home") { HomeScreen(navController, viewModel) }
            composable("profileSetup") { ProfileSetupScreen(navController, viewModel) }
        }
    }
}
