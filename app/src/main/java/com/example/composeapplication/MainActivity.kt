package com.example.composeapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
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

class MainActivity : Activity(),OnClickListener {

    override fun onCreate(savedInstanceStace: Bundle?) {
        super.onCreate(savedInstanceStace)
        setContentView(R.layout.main_activity)
        title = "MainActivity"
        Log.i("MainActivity", "onCreate")
        // 初始化 button1
        val button = findViewById<Button>(R.id.button1)
        button.setOnClickListener(this)
        // 获取保存的数据，并显示在textView2上
        if (savedInstanceStace != null) {
            val value = savedInstanceStace.getString("key")
            val textView = findViewById<TextView>(R.id.textView2)
            textView.text = value
        }
        // 加载广告页面
        val intent = Intent(this, AdActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "onPause")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("MainActivity", "onSaveInstanceState")
        outState.putString("key", "value");
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy")
    }

    override fun onClick(v: View?) {
        // 打开Second Activity
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
}
