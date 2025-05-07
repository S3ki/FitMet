package com.example.fitmet.screens

import android.content.Context
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepStatisticsScreen(navController: NavController) {
    val weeklySteps = listOf(
        "Monday" to 7000,
        "Tuesday" to 8500,
        "Wednesday" to 6000,
        "Thursday" to 9000,
        "Friday" to 7500,
        "Saturday" to 10000,
        "Sunday" to 6500
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weekly Steps", style = MaterialTheme.typography.titleLarge) }
            )
        },
        bottomBar = {
            Button(
                onClick = { navController.popBackStack() },
                shape = MaterialTheme.shapes.medium,
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
                text = "This Week's Steps",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            weeklySteps.forEach { (day, steps) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(day, style = MaterialTheme.typography.titleMedium)
                            Text("$steps steps", style = MaterialTheme.typography.bodyLarge)
                        }

                        // Animaatio tähti, kun askeleet > 8000
                        if (steps >= 8000) {
                            val scale by animateFloatAsState(
                                targetValue = 1.2f,
                                animationSpec = infiniteRepeatable(
                                    animation = tween(durationMillis = 800, easing = EaseInOutCubic),
                                    repeatMode = RepeatMode.Reverse
                                ),
                                label = "starAnimation"
                            )

                            Text(
                                text = "🌟",
                                fontSize = 30.sp, // Asetetaan fonttikoko suoraan
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.scale(scale) // Tässä käytetään scale-animaatiota
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(80.dp))

        }
    }
}

@Composable
fun ShowLineGraph(values: List<Entry>) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context: Context ->
            val view = LineChart(context)
            view.legend.isEnabled = false
            val data = LineData(LineDataSet(values, "steps"))
            val desc = Description()
            desc.text = "Steps this week"
            view.description = desc;
            view.data = data
            view // return the view
        },
        update = { view ->
            // Update the view
            view.invalidate()
        }
    )
}