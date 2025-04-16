package com.example.fitmet.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fitmet.R


@Composable
fun HomeMainScreen(userName: String = "Roger") {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFFAF0))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profiilikuva
        Image(
            painter = painterResource(id = R.drawable.intia), // Lisää profiilikuva drawable-kansioon
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Tervehdys
        Text(text = "Hi, $userName!", style = MaterialTheme.typography.headlineSmall)
        Text(text = "Ready to crush your goals today?")

        Spacer(modifier = Modifier.height(24.dp))

        // Tilastot
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            StatBox(icon = Icons.Filled.AccessTime, label = "2h 30m", subtitle = "Workout")
            StatBox(icon = Icons.Filled.DirectionsWalk, label = "12,000", subtitle = "Steps")
            StatBox(icon = Icons.Filled.LocalFireDepartment, label = "4,300", subtitle = "Calories")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Edistymispalkki
        Text("Your goal", style = MaterialTheme.typography.titleMedium)
        CircularProgressIndicator(progress = 0.75f, strokeWidth = 8.dp)
        Text("75%", fontWeight = FontWeight.Bold)
        Text("Almost there!")

        Spacer(modifier = Modifier.height(24.dp))

        // Saavutukset
        Text("Achievements", style = MaterialTheme.typography.titleMedium)
        BadgeItem(text = "25K steps badge!")
    }
}

@Composable
fun StatBox(icon: ImageVector, label: String, subtitle: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, contentDescription = subtitle, tint = Color.Black)
        Text(label, fontWeight = FontWeight.Bold)
        Text(subtitle)
    }
}

@Composable
fun BadgeItem(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color(0xFFFFD700), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("25K", fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(text)
    }
}
