package com.numenrich.app.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DashboardScreen(
    patientName: String,
    onOpenDiseases: () -> Unit,
    onOpenReadings: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        Text("Hi $patientName")

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onOpenDiseases,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("My diseases")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onOpenReadings,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("My readings")
        }
    }
}
