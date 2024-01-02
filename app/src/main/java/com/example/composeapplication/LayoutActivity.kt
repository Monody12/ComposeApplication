package com.example.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import com.example.composeapplication.ui.ConstraintLayoutExample
import com.example.composeapplication.ui.ConstraintLayoutExample4
import com.example.composeapplication.ui.IntrinsicLayoutExample
import com.example.composeapplication.ui.MainScreen
import com.example.composeapplication.ui.theme.ComposeApplicationTheme

class LayoutActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceStace: Bundle?) {
        super.onCreate(savedInstanceStace)
        setContent{
            ComposeApplicationTheme {
                IntrinsicLayoutExample()
            }
        }
    }
}