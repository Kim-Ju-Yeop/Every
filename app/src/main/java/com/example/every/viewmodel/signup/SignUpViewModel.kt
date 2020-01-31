package com.example.every.viewmodel.signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.every.network.request.model.signup.SignUpDataStudent
import com.example.every.network.request.model.signup.SignUpDataWorker
import com.example.every.widget.SingleLiveEvent

class SignUpViewModel : ViewModel(){

    // 0 : 학생 | 1 : 직장인
    val checkInfo = MutableLiveData<Int>()
    val onSuccessEvent = SingleLiveEvent<Unit>()

    fun clickEvent(info : Int){
        checkInfo.value = info
        onSuccessEvent.call()
    }
}