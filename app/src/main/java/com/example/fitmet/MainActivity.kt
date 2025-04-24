package com.example.fitmet

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.fitmet.screens.*
import com.example.fitmet.viewmodel.UserViewModel
import com.example.fitmet.ui.theme.FitMetTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkPermissions(this, this)

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
            composable("profileDetail") { ProfileDetailScreen(navController, viewModel) }
            composable("Homemain") { HomeMainScreen("Muha", viewModel = viewModel, navController = navController)  }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
private fun checkPermissions(context: Context, activity: MainActivity) {
    if (!hasRequiredPermissions(context)) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                android.Manifest.permission.ACTIVITY_RECOGNITION,
                android.Manifest.permission.FOREGROUND_SERVICE,
                android.Manifest.permission.FOREGROUND_SERVICE_HEALTH
            ),
            1
        )}

}


@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
 fun hasRequiredPermissions(context: Context): Boolean {
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