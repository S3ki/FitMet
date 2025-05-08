package com.example.fitmet.ui.screens

import android.content.Context
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.fitmet.data.Achievement
import com.example.fitmet.data.FitApp
import com.example.fitmet.viewmodel.UserViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementScreen(navController: NavController, viewModel: UserViewModel) {

    val sharedPref = FitApp.appContext.getSharedPreferences("fit_prefs", Context.MODE_PRIVATE)
    val userId = sharedPref.getInt("current_user_id", -1)

    val achievementList by viewModel.getAchievementsForUser(userId)
        .collectAsState(initial = emptyList())

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

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(achievementList) { ach ->
                    val achieved = ach.achieved
                    AchievementCard(ach, achieved = achieved)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
fun AchievementCard(achievement: Achievement, achieved: Boolean) {
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
                    text = "Level ${achievement.level}:",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = if (achieved) MaterialTheme.colorScheme.primary else Color.Gray
                )
                Text(
                    text = "Reach ${achievement.desc} ",
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
