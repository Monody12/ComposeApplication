package com.example.composeapplication

import android.app.Application
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

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

internal class AdvertisingViewModel(application: Application) :
    AndroidViewModel(application) {
    // 计时开始时间
    var millisInFuture : Long = 2000
}

/**
 * 广告页面
 * 页面start时计时，5秒后关闭页面
 */
class AdActivity : AppCompatActivity(), LifecycleOwner {

    private val TAG = "AdActivity"

    lateinit var textView: TextView

    private lateinit var lifecycleRegistry: LifecycleRegistry

    private lateinit var advertisingViewModel: AdvertisingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ad_activity)
        title = "AdActivity"
        Log.i(TAG, "onCreate")
        textView = findViewById(R.id.textView1)
        // lifecycle
        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
        // 创建一个计时器
        advertisingViewModel = ViewModelProvider(this)[AdvertisingViewModel::class.java]
        val countDownMillisInFuture = advertisingViewModel.millisInFuture
        val countDownTimer: CountDownTimer =
            object : CountDownTimer(countDownMillisInFuture, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    Log.i(TAG, "onTick: 广告还剩${millisUntilFinished}秒")
                    textView.text = "广告还剩${millisUntilFinished / 1000}秒"
                    advertisingViewModel.millisInFuture = millisUntilFinished
                }

                override fun onFinish() {
                    Log.i(TAG, "广告 onFinish")
                    MainActivity.actionStart(this@AdActivity)
                    finish()
                }
            }
        lifecycle.addObserver(MyLocationListener(countDownTimer))

    }

}