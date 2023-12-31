package com.example.composeapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/**
 * 自定义布局，实现不等边距的Column
 */
@Composable
fun MyColumnLayout(content: @Composable () -> Unit) {
    Layout(
        content = content,
        modifier = Modifier
    ) { measurables, constraints ->
        // Measure children
        val placeables  = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        // 布局的大小
        layout(constraints.maxWidth, constraints.maxHeight){
            var yPosition = 0
            // 间距大小
            val spacing = 8.dp.toPx()

            placeables.forEachIndexed { index, placeable ->
                // 偶数为默认间距，奇数为双倍间距
                yPosition += if(index % 2 == 0){
                    placeable.placeRelative(x = 0, y = yPosition)
                    placeable.height + spacing.toInt()
                }else{
                    placeable.placeRelative(x = 0, y = yPosition)
                    placeable.height + spacing.toInt() * 2
                }
            }
        }

    }
}

@Composable
fun AlternatingWidthLayoutExample() {
    MyColumnLayout {
        repeat(3){
            Text(text = "文本1")

            Text(text = "文本2")

            Text(text = "文本3")
        }
    }
}


@Preview
@Composable
fun AlternatingWidthLayoutExamplePreview() {
    AlternatingWidthLayoutExample()
}