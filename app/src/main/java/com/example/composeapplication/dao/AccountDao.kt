package com.example.composeapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.composeapplication.bean.AccountBean

@Dao
interface AccountDao {

    /**
     * 插入账号
     */
    @Insert
    fun insertAccount(accountBean: AccountBean)

    /**
     * 查询账号列表
     */
    @Query("SELECT * FROM AccountBean")
    fun queryAccountList(): List<AccountBean>


}