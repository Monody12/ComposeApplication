package com.example.composeapplication.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapplication.CompositionActivity
import com.example.composeapplication.FifthActivity
import com.example.composeapplication.FourthActivity
import com.example.composeapplication.LayoutActivity
import com.example.composeapplication.SecondActivity
import com.example.composeapplication.ThirdActivity

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            // Content for the top bar (app bar)
            TopAppBar(title = { Text("My App MainScreen") })
        },
        bottomBar = {
            // Content for the bottom bar
            BottomAppBar {
                // Bottom bar content
            }
        },
        floatingActionButton = {
            // Content for the floating action button
        },
        content = {
            // Content for the screen
            MyScaffoldContent(modifier = Modifier.padding(it))
        }
    )

}

@Composable
fun MyScaffoldContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    // Your main content goes here
//    Text(text = "Hello, world!")
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        // Your main content goes here
        Column(
            modifier = Modifier
                .background(Color.White)
        ) {
            Text(text = "Hello, world!", modifier = Modifier.padding(16.dp), fontSize = 24.sp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        val intent = Intent(context, SecondActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 20.dp)
                ) {
                    Text(text = "SecondActivity")
                }
                Button(
                    onClick = {
                        val intent = Intent(context, ThirdActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 20.dp)
                ) {
                    Text(text = "ThirdActivity")
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        val intent = Intent(context, FourthActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 20.dp)
                ) {
                    Text(text = "FourthActivity")
                }
                Button(
                    onClick = {
                        val intent = Intent(context, FifthActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 20.dp)
                ) {
                    Text(text = "FifthActivity")
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        val intent = Intent(context, LayoutActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 20.dp)
                ) {
                    Text(text = "LayoutActivity")
                }
                Button(
                    onClick = {
                        val intent = Intent(context, CompositionActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 20.dp)
                ) {
                    Text(text = "Button6")
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}