package com.example.composeapplication.bean

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.composeapplication.BR


class User : BaseObservable(){
    @get:Bindable
    var username: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.username)
        }
    @get:Bindable
    var password: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }
}