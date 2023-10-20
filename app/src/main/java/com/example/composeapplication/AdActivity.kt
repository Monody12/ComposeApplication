package com.example.composeapplication

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

/**
 * 广告页面
 * 页面start时计时，5秒后关闭页面
 */
internal class MyLocationListener(
    private var countDownTimer: CountDownTimer?
) : DefaultLifecycleObserver {

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.i("MyObserver", "onStart")
        countDownTimer?.start()
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.i("MyObserver", "onStop")
        countDownTimer?.cancel()
        countDownTimer = null
    }
}


class AdActivity : AppCompatActivity(),LifecycleOwner {

    private val TAG = "AdActivity"

    lateinit var textView: TextView

    private lateinit var lifecycleRegistry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ad_activity)
        title = "AdActivity"
        Log.i(TAG, "onCreate")
        textView = findViewById(R.id.textView1)
        // lifecycle
        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
        lifecycle.addObserver(MyLocationListener(countDownTimer))
    }

    private var countDownTimer: CountDownTimer? = object : CountDownTimer(6000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            Log.i(TAG, "onTick: 广告还剩$millisUntilFinished")
            textView.text = "广告还剩${millisUntilFinished / 1000}秒"
        }

        override fun onFinish() {
            Log.i(TAG, "广告 onFinish")
            finish()
        }
    }


}