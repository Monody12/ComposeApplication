package com.example.composeapplication.network

class Response<T>(
    val data: T?,
    val code: Int,
    val msg: String
)
