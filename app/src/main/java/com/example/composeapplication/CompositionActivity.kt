package com.example.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composeapplication.ui.CompositionScreen
import com.example.composeapplication.ui.theme.ComposeApplicationTheme

class CompositionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                CompositionScreen()
            }
        }
    }

}