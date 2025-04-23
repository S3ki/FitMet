package com.example.fitmet.screens



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.fitmet.R
import com.example.fitmet.viewmodel.UserViewModel


@Composable
fun HomeMainScreen(userName: String = "Muha", viewModel: UserViewModel ,navController: NavController) {

    val userProfile = viewModel.userProfile.value
    var showProfileDropdown by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        IconButton(onClick = { showProfileDropdown = true }) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile",
                tint = MaterialTheme.colorScheme.secondary
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
            StatBox(icon = Icons.Filled.AccessTime, label = "0 min", subtitle = "Workout")
            StatBox(icon = Icons.Filled.DirectionsWalk, label = "0", subtitle = "Steps")
            StatBox(icon = Icons.Filled.LocalFireDepartment, label = "0", subtitle = "Calories")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Edistymispalkki
        Text("Your goal", style = MaterialTheme.typography.titleMedium)
        CircularProgressIndicator(progress = 0f, strokeWidth = 8.dp)
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