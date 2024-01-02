package com.example.composeapplication.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.composeapplication.ui.theme.ComposeApplicationTheme


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
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        // 布局的大小
        layout(constraints.maxWidth, constraints.maxHeight) {
            var yPosition = 0
            // 间距大小
            val spacing = 8.dp.toPx()

            placeables.forEachIndexed { index, placeable ->
                // 偶数为默认间距，奇数为双倍间距
                yPosition += if (index % 2 == 0) {
                    placeable.placeRelative(x = 0, y = yPosition)
                    placeable.height + spacing.toInt()
                } else {
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
        repeat(3) {
            Text(text = "文本1")

            Text(text = "文本2")

            Text(text = "文本3")
        }
    }
}


/**
 * 自定义约束布局使用示例
 */
@Composable
fun ConstraintLayoutExample() {
    Column {
        ConstraintLayout {
            // 通过createRef创建引用，ConstraintLayout中的每个元素都需要关联一个引用
            val (button, text) = createRefs()
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.constrainAs(button) {
                    // 指定button的位置
                    top.linkTo(parent.top, margin = 16.dp)
                }
            ) {
                Text(text = "Button")
            }
            // 文本的顶部就是按钮的底部
            Text(text = "Text", modifier = Modifier.constrainAs(text) {
                top.linkTo(button.bottom, margin = 16.dp)
                // 在ConstraintLayout中水平居中
                centerHorizontallyTo(parent)
            })
        }
        Box(
            modifier = Modifier
                .height(16.dp)
                .background(color = Color.Red)
                .fillMaxWidth()
        )
        ConstraintLayoutExample2()
    }
}

@Composable
fun ConstraintLayoutExample2() {
    ConstraintLayout {
        // 通过createRef创建引用，ConstraintLayout中的每个元素都需要关联一个引用
        val (button1, button2, text) = createRefs()
        // 两个按钮在第一行，中间隔开。一个Text在这两个按钮下方的正中间。
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(button2.start)
                bottom.linkTo(text.top, margin = 16.dp)
            }
        ) {
            Text(text = "Button1")
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(button1.end, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                bottom.linkTo(text.top, margin = 16.dp)
            }
        ) {
            Text(text = "Button2")
        }
        Text(
            modifier = Modifier.constrainAs(text) {
                top.linkTo(button1.bottom)
                bottom.linkTo(parent.bottom)
                // 水平居中
                centerHorizontallyTo(parent)
            },
            text = "Text"
        )
    }
}

@Composable
fun ConstraintLayoutExample3() {
    ConstraintLayout {
        val (button1, button2, text) = createRefs()

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(button1) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    // Remove bottom constraint
                }
                .padding(horizontal = 10.dp)
        ) {
            Text(text = "Button1")
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(button2) {
                    top.linkTo(parent.top, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    // Remove bottom constraint
                }
                .padding(horizontal = 10.dp)
        ) {
            Text(text = "Button2")
        }

        // Create a horizontal chain for the buttons to distribute the space evenly between them
        createHorizontalChain(button1, button2, chainStyle = ChainStyle.Packed)

        Text(
            modifier = Modifier.constrainAs(text) {
                top.linkTo(button1.bottom, margin = 16.dp)
                centerHorizontallyTo(parent)
                // Remove bottom constraint to parent
            },
            text = "Text"
        )
    }
}

/**
 * 导向线的使用案例
 */
@Composable
fun ConstraintLayoutExample4() {
    ConstraintLayout {
        val (text1, text2) = createRefs()
        val guideline = createGuidelineFromStart(fraction = 0.5f)
        val veryLongText = "This is a very very very very very very very very very very long Text"
        Text(
            text = veryLongText,
            modifier = Modifier.constrainAs(text1) {
                linkTo(start = parent.start, end = guideline)
                width = Dimension.preferredWrapContent
            }
        )
        Text(
            text = veryLongText,
            modifier = Modifier.constrainAs(text2) {
                linkTo(start = guideline, end = parent.end)
                width = Dimension.preferredWrapContent
            }
        )
    }
}

@Composable
fun IntrinsicLayoutExample(modifier: Modifier = Modifier) {
    // 在Row中添加 .height(IntrinsicSize.Max) 修饰以使Row重设高度
    Row(modifier = modifier.height(IntrinsicSize.Min)) {
        Text(
            text = "文本1",
            modifier = modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start)
        )
        Divider(color = Color.Black, modifier = Modifier
            .fillMaxHeight()
            .width(1.dp))
        Text(
            text = "文本2",
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.End)
        )
    }
}


@Preview
@Composable
fun MyPreview() {
    ComposeApplicationTheme {
        IntrinsicLayoutExample()
    }
}