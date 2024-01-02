package com.example.composeapplication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun CompositionScreen() {
    MaterialTheme{
        val textContent =
            "如需为 CompositionLocal 提供新值，请使用 CompositionLocalProvider 及其 provides infix 函数"
        Column {
            Text(text = textContent)
            // 使用 CompositionLocalProvider 修改Text的透明度
            CompositionLocalProvider(
                LocalContentColor.provides(
                    MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.34f
                    )
                )
            ) {
                Text(text = textContent)
                Text(text = textContent)
                // 嵌套 Provider
                CompositionLocalProvider(
                    LocalContentColor.provides(
                        MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.20f
                        )
                    )
                ){
                    Text(text = textContent)
                }
            }

        }
    }


}