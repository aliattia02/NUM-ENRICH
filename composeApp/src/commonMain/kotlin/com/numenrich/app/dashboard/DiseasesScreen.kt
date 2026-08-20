package com.numenrich.app.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DiseasesScreen(onBack: () -> Unit) {
    var name by remember { mutableStateOf("") }
    val diseases = remember { mutableStateListOf<Disease>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        TextButton(onClick = onBack) {
            Text("Back")
        }

        Text("My diseases")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Disease name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (name.isNotBlank()) {
                diseases.add(Disease(name))
                name = ""
            }
        }) {
            Text("Add disease")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (diseases.isEmpty()) {
            Text("No diseases added yet.")
        } else {
            LazyColumn {
                items(diseases) { disease ->
                    Text(disease.name)
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}
