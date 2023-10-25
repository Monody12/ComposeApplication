package com.example.composeapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composeapplication.bean.AccountBean
import com.example.composeapplication.dao.AccountDao

@Database(entities = [AccountBean::class], version = 1)
abstract class AccountDatabase : RoomDatabase(){
    abstract val accountDao: AccountDao

}