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
import com.example.fitmet.screens.*
import com.example.fitmet.viewmodel.UserViewModel
import com.example.fitmet.ui.theme.FitMetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            composable("home") { HomeScreen() }
        }
    }
}
