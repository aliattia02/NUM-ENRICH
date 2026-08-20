package com.numenrich.app.consent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConsentScreen(
    userName: String,
    onAgree: () -> Unit
) {
    var agreed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        Text("Consent")

        Spacer(modifier = Modifier.height(16.dp))

        Text("Hi $userName, before you continue, please read this:")

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "NUM-ENRICH stores the readings you enter directly on this " +
                "device so you can look back at them later. This is a " +
                "sample consent text for now, not the real legal wording."
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = agreed, onCheckedChange = { agreed = it })
            Text("I agree")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onAgree,
            enabled = agreed,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue")
        }
    }
}
