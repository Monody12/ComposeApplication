package com.example.composeapplication

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.example.composeapplication.databinding.ThirdActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import javax.inject.Inject

@AndroidEntryPoint
class ThirdActivity: ComponentActivity() {

    private lateinit var thirdActivityBinding: ThirdActivityBinding

    @Inject
    lateinit var okHttpClient: OkHttpClient

    override fun onCreate(savedInstanceStace: Bundle?){
        super.onCreate(savedInstanceStace)
        // 获取绑定
        thirdActivityBinding = ThirdActivityBinding.inflate(layoutInflater)
        setContentView(thirdActivityBinding.root)
        title = "ThirdActivity"

    }

    fun loadUrl(view: View){
        val url = thirdActivityBinding.edUrl.text
        val request = okhttp3.Request.Builder().url(url.toString()).build()
        okHttpClient.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                val error = e.toString()
                runOnUiThread {
                    // 在 UI 线程上更新视图
                    thirdActivityBinding.tvResult.text = error
                }
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val res = response.body?.string() // .string()方法不能放在ui线程中执行
                runOnUiThread {
                    // 在 UI 线程上更新视图
                    thirdActivityBinding.tvResult.text = res
                }
            }
        })
    }


}