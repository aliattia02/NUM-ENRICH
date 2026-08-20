package com.numenrich.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.numenrich.app.auth.Patient
import com.numenrich.app.auth.RegisterScreen
import com.numenrich.app.auth.SignInScreen
import com.numenrich.app.consent.ConsentScreen
import com.numenrich.app.dashboard.DashboardScreen
import com.numenrich.app.dashboard.DiseasesScreen

private enum class Screen {
    SignIn,
    Register,
    Consent,
    Dashboard,
    Diseases,
    Readings
}

@Composable
fun App() {
    val patients = remember { mutableStateListOf<Patient>() }
    var currentPatient by remember { mutableStateOf<Patient?>(null) }
    var screen by remember { mutableStateOf(Screen.SignIn) }

    when (screen) {
        Screen.SignIn -> SignInScreen(
            users = patients,
            onSignInSuccess = { patient ->
                currentPatient = patient
                screen = Screen.Consent
            },
            onGoToRegister = { screen = Screen.Register }
        )

        Screen.Register -> RegisterScreen(
            onRegister = { patient ->
                patients.add(patient)
                currentPatient = patient
                screen = Screen.Consent
            },
            onGoToSignIn = { screen = Screen.SignIn }
        )

        Screen.Consent -> ConsentScreen(
            userName = "${currentPatient?.givenName ?: ""} ${currentPatient?.familyName ?: ""}".trim(),
            onAgree = { screen = Screen.Dashboard }
        )

        Screen.Dashboard -> DashboardScreen(
            patientName = "${currentPatient?.givenName ?: ""} ${currentPatient?.familyName ?: ""}".trim(),
            onOpenDiseases = { screen = Screen.Diseases },
            onOpenReadings = { screen = Screen.Readings }
        )

        Screen.Diseases -> DiseasesScreen(
            onBack = { screen = Screen.Dashboard }
        )

        Screen.Readings -> ReadingsScreen(
            onBack = { screen = Screen.Dashboard }
        )
    }
}
