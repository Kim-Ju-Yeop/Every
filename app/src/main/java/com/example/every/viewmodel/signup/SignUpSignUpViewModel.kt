package com.example.every.viewmodel.signup

import androidx.lifecycle.MutableLiveData
import com.example.every.viewmodel.base.BaseSignUpViewModel

class SignUpSignUpViewModel : BaseSignUpViewModel(){

    // 0 : 학생 | 1 : 직장인
    val checkInfo = MutableLiveData<Int>()

    fun clickEvent(info : Int){
        checkInfo.value = info
        onSuccessEvent.call()
    }
}