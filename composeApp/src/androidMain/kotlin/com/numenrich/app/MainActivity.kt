package com.numenrich.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.numenrich.app.db.NumEnrichDatabase

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val driver = AndroidSqliteDriver(NumEnrichDatabase.Schema, applicationContext, "numenrich.db")
        val database = NumEnrichDatabase(driver)

        setContent {
            MaterialTheme {
                App(database)
            }
        }
    }
}
