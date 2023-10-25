package com.example.composeapplication.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccountBean(
    @PrimaryKey(autoGenerate = true)
    var accountId : Int = 0,
    var loginAccount: String,
    var loginPassword: String
)