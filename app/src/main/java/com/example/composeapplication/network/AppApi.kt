package com.example.composeapplication.network

import com.example.composeapplication.bean.ShiftInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface AppApi {


    @GET("ticket/check")
    suspend fun getCheckTicket(
        @Query("date") date:String,
        @Query("checkType") checkType:Int
    ) : Response<MutableList<ShiftInfo>>

}