package com.numenrich.app.auth

data class Patient(
    val subjectId: String,
    val localPatientRef: String,
    val givenName: String,
    val familyName: String,
    val birthDate: String,
    val userType: String = "patient",
    val email: String,
    val password: String
)
