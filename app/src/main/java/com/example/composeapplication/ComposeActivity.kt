package com.example.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapplication.ui.theme.ComposeApplicationTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    News()
                }
            }
        }
    }
}


@Composable
fun News(){
    Column {
        LazyColumn(content = {
            for (i in 0..30) {
                item {
                    Row(Modifier.padding(20.dp)){
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription ="this is a image"
                        )
                        Column (Modifier.padding(horizontal = 20.dp)){
                            Text(text = "this is a title，${i}", fontSize = 20.sp, color = Color.Black)
                            Text(text = "this is a content", fontSize = 18.sp, color = Color.DarkGray)
                        }
                    }
                }
            }
        })
    }

}

@Preview(showBackground = true)
@Composable
fun NewsPreview(){
    ComposeApplicationTheme {
        News()
    }
}