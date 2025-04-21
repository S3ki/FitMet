package com.example.fitmet

import android.content.Context
import android.content.pm.PackageManager
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
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!hasRequiredPermissions(this)) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACTIVITY_RECOGNITION,
                    android.Manifest.permission.FOREGROUND_SERVICE,
                    android.Manifest.permission.FOREGROUND_SERVICE_HEALTH
                ),
                1
            )}


        enableEdgeToEdge()
        setContent {
            FitMetTheme {
                StepCounterStatus()
            }
        }
    }

}

@Composable
fun StepCounterStatus() {
        StepCounterScreen()
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
        }
    }
}







@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
private fun hasRequiredPermissions(context: Context): Boolean {
    val activityRecognitionPermission = ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACTIVITY_RECOGNITION
    ) == PackageManager.PERMISSION_GRANTED

    val foregroundServicePermission = ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.FOREGROUND_SERVICE
    ) == PackageManager.PERMISSION_GRANTED

    val foregroundHealthPermission = ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.FOREGROUND_SERVICE_HEALTH
    ) == PackageManager.PERMISSION_GRANTED

    return activityRecognitionPermission &&
            foregroundServicePermission &&
            foregroundHealthPermission
}

