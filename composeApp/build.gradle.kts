plugins {
    kotlin("multiplatform") version "2.4.10"
    id("org.jetbrains.kotlin.plugin.compose") version "2.4.10"
    id("com.android.application") version "8.9.1"
    id("org.jetbrains.compose") version "1.11.1"
    id("app.cash.sqldelight") version "2.0.2"
}

kotlin {
    androidTarget()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation("app.cash.sqldelight:runtime:2.0.2")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.activity:activity-compose:1.10.1")
                implementation("app.cash.sqldelight:android-driver:2.0.2")
            }
        }
    }
}

sqldelight {
    databases {
        create("NumEnrichDatabase") {
            packageName.set("com.numenrich.app.db")
        }
    }
}

android {
    namespace = "com.numenrich.app"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.numenrich.starter"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "0.1"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}
