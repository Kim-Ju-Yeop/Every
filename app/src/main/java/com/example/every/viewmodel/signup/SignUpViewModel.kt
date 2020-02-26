package com.example.every.viewmodel.signup

import androidx.lifecycle.MutableLiveData
import com.example.every.base.BaseViewModel

class SignUpViewModel : BaseViewModel(){

    // 0 : 학생 | 1 : 직장인
    val checkInfo = MutableLiveData<Int>()

    fun clickEvent(info : Int){
        checkInfo.value = info
        onNextEvent.call()
    }
}