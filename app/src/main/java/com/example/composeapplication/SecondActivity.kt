package com.example.composeapplication

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

internal class TipDialog(context: Context): Dialog(context) ,LifecycleObserver{

    init {
        if (context is ComponentActivity) {
            context.lifecycle.addObserver(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_tip_dialog)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        if (isShowing) {
            dismiss()
            Log.i("TipDialog", "onDestroy")
        }
    }
}

class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceStace: Bundle?) {
        super.onCreate(savedInstanceStace)
        setContentView(R.layout.second_activity)
        setTitle("SecondActivity")
        Log.i("SecondActivity", "onCreate")

        // 测试Dialog内存泄露
        TipDialog(this).show()
        Handler().postDelayed({
            finish()
        }, 2000)
    }

    override fun onStart() {
        super.onStart()
        Log.i("SecondActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("SecondActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("SecondActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("SecondActivity", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("SecondActivity", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("SecondActivity", "onDestroy")
    }
}