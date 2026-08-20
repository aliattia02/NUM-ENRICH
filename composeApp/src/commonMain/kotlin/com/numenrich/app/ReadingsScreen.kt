package com.numenrich.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingsScreen(onBack: () -> Unit) {
    var label by remember { mutableStateOf("") }
    var value by remember { mutableStateOf("") }
    val readings = remember { mutableStateListOf<Reading>() }

    Scaffold(
        topBar = { TopAppBar(title = { Text("NUM-ENRICH") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            TextButton(onClick = onBack) {
                Text("Back")
            }

            OutlinedTextField(
                value = label,
                onValueChange = { label = it },
                label = { Text("Label") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = value,
                onValueChange = { value = it },
                label = { Text("Value") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                if (label.isNotBlank() && value.isNotBlank()) {
                    readings.add(Reading(label, value))
                    label = ""
                    value = ""
                }
            }) {
                Text("Add reading")
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(readings) { reading ->
                    Text("${reading.label}: ${reading.value}")
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}
