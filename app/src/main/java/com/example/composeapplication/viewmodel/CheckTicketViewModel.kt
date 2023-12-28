package com.example.composeapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapplication.bean.ShiftInfo
import com.example.composeapplication.network.RetrofitManger
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CheckTicketViewModel : ViewModel() {

    val api by lazy { RetrofitManger.getApiService() }
    var shiftInfoLiveData : MutableLiveData<MutableList<ShiftInfo>> = MutableLiveData()

    var apiError:MutableLiveData<Throwable> = MutableLiveData()

    fun getCheckTicket(date:String,checkType:Int){
        val exception = CoroutineExceptionHandler { coroutineContext, throwable ->
            apiError.postValue(throwable)
            Log.i("CoroutinesViewModel",throwable.message!!)
        }

        viewModelScope.launch(exception) {
            val respose = api.getCheckTicket(date, checkType)
            if (respose.code == 200) {
                val data : MutableList<ShiftInfo> = respose.data!!
                // 在主线程上更新数据
                withContext(Dispatchers.Main){
                    shiftInfoLiveData.postValue(data)
                    // 查看shiftInfoLiveData中的内容
                    Log.i("CoroutinesViewModel",shiftInfoLiveData.value.toString())
                }
            } else {
                shiftInfoLiveData.postValue(mutableListOf())
            }
        }

    }

}

