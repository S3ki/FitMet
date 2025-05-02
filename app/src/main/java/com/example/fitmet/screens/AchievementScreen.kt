package com.example.fitmet.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitmet.R
import com.example.fitmet.viewmodel.UserViewModel

// Tietoluokka tasoille
data class LevelInfo(val levelNumber: Int, val distance: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementScreen(navController: NavController, viewModel: UserViewModel) {
    val userProfile = viewModel.userProfile
    val progress = userProfile?.distanceCovered ?: 0

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Achievements", style = MaterialTheme.typography.titleLarge) }
            )
        },
        bottomBar = {
            Button(
                onClick = { navController.popBackStack() },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp)
            ) {
                Text("Back to Home", fontSize = 18.sp)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Your Journey",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            val levels = listOf(
                LevelInfo(1, 5),
                LevelInfo(2, 10),
                LevelInfo(3, 15),
                LevelInfo(4, 20),
                LevelInfo(5, 25)
            )

            levels.forEach { levelInfo ->
                val achieved = progress >= levelInfo.distance
                AchievementCard(levelInfo = levelInfo, achieved = achieved)
                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(80.dp)) // Jotta alin kortti ei mene napin alle
        }
    }
}

@Composable
fun AchievementCard(levelInfo: LevelInfo, achieved: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (achieved) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text(
                    text = "Level ${levelInfo.levelNumber}:",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = if (achieved) MaterialTheme.colorScheme.primary else Color.Gray
                )
                Text(
                    text = "Reach ${levelInfo.distance} km",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (achieved) MaterialTheme.colorScheme.primary else Color.Gray
                )
            }

            if (achieved) {
                val scale by animateFloatAsState(
                    targetValue = 1.2f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 800, easing = EaseInOutCubic),
                        repeatMode = RepeatMode.Reverse
                    ), label = "scaleAnimation"
                )

                Image(
                    painter = painterResource(id = R.drawable.reward_token),
                    contentDescription = "Reward Token",
                    modifier = Modifier
                        .size(56.dp)
                        .scale(scale)
                )
            }
        }
    }
}
