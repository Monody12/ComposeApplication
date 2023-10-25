package com.example.composeapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import com.example.composeapplication.databinding.MainActivityBinding

class MainActivity : Activity(),OnClickListener {

    private lateinit var mainActivityBinding: MainActivityBinding

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceStace: Bundle?) {
        super.onCreate(savedInstanceStace)
        Log.i("MainActivity", "onCreate")
        title = "MainActivity"
        // 使用 ViewBinding 来获取控件
        mainActivityBinding = MainActivityBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        // 初始化 button1
        mainActivityBinding.button1.setOnClickListener(this)
        // 获取保存的数据，并显示在textView2上
        if (savedInstanceStace != null) {
            val value = savedInstanceStace.getString("key")
            mainActivityBinding.textView2.text = value
        }
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
