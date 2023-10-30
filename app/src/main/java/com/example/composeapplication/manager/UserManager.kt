package com.example.composeapplication.manager

import android.util.Log
import javax.inject.Inject

class UserManager @Inject constructor(){
    val TAG = "UserManager"
    fun getToken(){
        Log.i(TAG, "获取用户token")
    }
}