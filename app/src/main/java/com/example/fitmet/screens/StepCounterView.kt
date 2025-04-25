package com.example.fitmet.screens

import StepCounter
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun StepCounterScreen() {
    val context = LocalContext.current
    var steps by remember { mutableStateOf<Long?>(null) }

    LaunchedEffect(Unit) {
        val counter = StepCounter(context)
        steps = counter.steps()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Step Counter", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        if (steps != null) {
            Text("Steps since boot: $steps")
        } else {
            CircularProgressIndicator()
        }
    }
}
