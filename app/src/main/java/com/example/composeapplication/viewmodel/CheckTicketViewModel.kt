package com.example.composeapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapplication.bean.ShiftInfo
import com.example.composeapplication.network.RetrofitManger
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class CheckTicketViewModel : ViewModel() {

    val api by lazy { RetrofitManger.getApiService() }
    val shiftInfoLiveData: MutableLiveData<MutableList<ShiftInfo>> by lazy {
        MutableLiveData<MutableList<ShiftInfo>>()
    }
//    val shiftInfoLiveData : MutableLiveData<MutableList<ShiftInfo>> = MutableLiveData()

    var apiError: MutableLiveData<Throwable> = MutableLiveData()

    fun getCheckTicket(date: String, checkType: Int) {
        val exception = CoroutineExceptionHandler { coroutineContext, throwable ->
            apiError.postValue(throwable)
            Log.i("CoroutinesViewModel", throwable.message!!)
        }

        viewModelScope.launch(exception) {
            val respose = api.getCheckTicket(date, checkType)
            if (respose.code == 200) {
                val data: MutableList<ShiftInfo> = respose.data!!
                shiftInfoLiveData.postValue(data)
            } else {
                apiError.postValue(Throwable(respose.msg))
            }
        }

    }

}

