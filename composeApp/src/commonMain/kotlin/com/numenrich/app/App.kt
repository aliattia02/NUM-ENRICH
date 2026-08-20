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
import com.numenrich.app.dashboard.Disease
import com.numenrich.app.dashboard.DiseasesScreen
import com.numenrich.app.db.NumEnrichDatabase

private enum class Screen {
    SignIn,
    Register,
    Consent,
    Dashboard,
    Diseases,
    Readings
}

@Composable
fun App(database: NumEnrichDatabase) {
    val patientQueries = database.patientAccountQueries
    val diseaseQueries = database.diseaseEntryQueries

    val patients = remember {
        mutableStateListOf<Patient>().apply {
            patientQueries.selectAll().executeAsList().forEach { row ->
                add(
                    Patient(
                        subjectId = row.subjectId,
                        localPatientRef = row.localPatientRef,
                        givenName = row.givenName,
                        familyName = row.familyName,
                        birthDate = row.birthDate,
                        gender = row.gender,
                        userType = row.userType,
                        email = row.email,
                        password = row.password
                    )
                )
            }
        }
    }
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
                patientQueries.insert(
                    subjectId = patient.subjectId,
                    localPatientRef = patient.localPatientRef,
                    givenName = patient.givenName,
                    familyName = patient.familyName,
                    birthDate = patient.birthDate,
                    gender = patient.gender,
                    userType = patient.userType,
                    email = patient.email,
                    password = patient.password
                )
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

        Screen.Diseases -> {
            val subjectId = currentPatient?.subjectId ?: ""
            val diseases = remember(subjectId) {
                mutableStateListOf<Disease>().apply {
                    diseaseQueries.selectForPatient(subjectId).executeAsList().forEach { row ->
                        add(Disease(row.name))
                    }
                }
            }

            DiseasesScreen(
                diseases = diseases,
                onAddDisease = { name ->
                    diseaseQueries.insert(patientSubjectId = subjectId, name = name)
                    diseases.add(Disease(name))
                },
                onBack = { screen = Screen.Dashboard }
            )
        }

        Screen.Readings -> ReadingsScreen(
            onBack = { screen = Screen.Dashboard }
        )
    }
}
