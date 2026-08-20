package com.numenrich.app.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun RegisterScreen(
    onRegister: (Patient) -> Unit,
    onGoToSignIn: () -> Unit
) {
    var givenName by remember { mutableStateOf("") }
    var familyName by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        Text("Register as a patient")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = givenName,
            onValueChange = { givenName = it },
            label = { Text("First name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = familyName,
            onValueChange = { familyName = it },
            label = { Text("Last name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = birthDate,
            onValueChange = { birthDate = it },
            label = { Text("Birth date (YYYY-MM-DD)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        if (error.isNotBlank()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            when {
                givenName.isBlank() || familyName.isBlank() || birthDate.isBlank() ||
                    email.isBlank() || password.isBlank() -> {
                    error = "Please fill in all fields."
                }
                password != confirmPassword -> {
                    error = "Passwords don't match."
                }
                else -> {
                    error = ""
                    val subjectId = "P-" + Random.nextInt(100000, 999999)
                    onRegister(
                        Patient(
                            subjectId = subjectId,
                            localPatientRef = email,
                            givenName = givenName,
                            familyName = familyName,
                            birthDate = birthDate,
                            email = email,
                            password = password
                        )
                    )
                }
            }
        }) {
            Text("Register")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = onGoToSignIn) {
            Text("Already have an account? Sign in")
        }
    }
}
