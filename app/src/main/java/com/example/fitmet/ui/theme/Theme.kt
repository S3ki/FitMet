// Theme.kt
package com.example.fitmet.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext



@Composable
fun FitMetTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Käytetään oletusasetusta, jos ei anneta arvoa
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
