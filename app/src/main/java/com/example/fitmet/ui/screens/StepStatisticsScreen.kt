package com.example.fitmet.ui.screens

import android.content.Context
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.fitmet.data.FitApp
import com.example.fitmet.data.Steps
import com.example.fitmet.viewmodel.UserViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepStatisticsScreen(navController: NavController, viewModel: UserViewModel) {

    val sharedPref = FitApp.appContext.getSharedPreferences("fit_prefs", Context.MODE_PRIVATE)
    val userId = sharedPref.getInt("current_user_id", -1)

    val stepsList by viewModel.getStepForUser(userId).collectAsState(initial = emptyList())

    val entries = stepsList.reversed().mapIndexed { index, data ->
        Entry(index.toFloat(), data.stepCount.toFloat())
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Steps", style = MaterialTheme.typography.titleLarge) }
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

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(stepsList) { step ->
                    StepCard(step)
                }
            }

            Box(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
            ) {
                if (entries.isNotEmpty()) {
                    ShowLineGraph(entries)
                } else {
                Text("No data, maybe go on a run for a couple of days 🤔 ",style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(16.dp))
            }            }
        }
    }
}

@Composable
fun StepCard(step: Steps) {
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
                Text(step.date, style = MaterialTheme.typography.titleMedium)
                Text("${step.stepCount} steps", style = MaterialTheme.typography.bodyLarge)
            }

            // Animaatio tähti, kun askeleet > 8000
            if (step.stepCount >= 8000) {
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
}

@Composable
fun ShowLineGraph(values: List<Entry>) {
    AndroidView(
        modifier = Modifier
            .fillMaxSize(),
        factory = { context: Context ->
            val view = LineChart(context)
            view.legend.isEnabled = false
            val data = LineData(LineDataSet(values, "steps"))
            val desc = Description()
            desc.text = "Steps this week"
            view.description = desc
            view.data = data
            view // return the view
        },
        update = { view ->
            // Update the view
            view.invalidate()
        }
    )
}