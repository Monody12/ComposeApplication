package com.example.composeapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.room.Room
import com.example.composeapplication.bean.AccountBean
import com.example.composeapplication.bean.User
import com.example.composeapplication.database.AccountDatabase
import com.example.composeapplication.databinding.SecondActivityBinding
import com.example.composeapplication.manager.UserManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SecondActivity : ComponentActivity() {

    private lateinit var secondActivityBinding: SecondActivityBinding

    private lateinit var db : AccountDatabase

    @Inject
    lateinit var userManager: UserManager

    override fun onCreate(savedInstanceStace: Bundle?) {
        super.onCreate(savedInstanceStace)
        // 获取绑定
        secondActivityBinding = SecondActivityBinding.inflate(layoutInflater)
        setContentView(secondActivityBinding.root)
        val user = User()
        secondActivityBinding.user = user
        setTitle("SecondActivity")
        Log.i("SecondActivity", "onCreate")
        // 初始化数据库
        db = Room.databaseBuilder(
            this,
            AccountDatabase::class.java, "account_database"
        ).allowMainThreadQueries().build()

    }

    fun confirm(view:View){
        // 从数据库中获取全部的账号
        val accountList = db.accountDao.queryAccountList()
        // 存入 tv_result
        secondActivityBinding.tvResult.text = accountList.toString()
    }

    fun register(view:View){
        // 获取用户token
        userManager.getToken()
        // 获取用户名和密码
        val username = secondActivityBinding.edUsername.text.toString()
        val password = secondActivityBinding.edPassword.text.toString()
        // 插入到数据库
        val accountBean = AccountBean(loginAccount= username,loginPassword= password)
        db.accountDao.insertAccount(accountBean)
    }
}