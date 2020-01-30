package com.example.every.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.every.widget.SingleLiveEvent

class MainViewModel : ViewModel(){

    val onSuccessEvent = SingleLiveEvent<Unit>()
    val onFailEvent = SingleLiveEvent<Unit>()

    // Login 여부 확인
    fun checkLogin(loginData : SharedPreferences){
        if(loginData.getBoolean("loginData", false)) onSuccessEvent.call() else onFailEvent.call()
    }
}