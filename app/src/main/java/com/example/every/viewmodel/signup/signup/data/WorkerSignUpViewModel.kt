package com.example.every.viewmodel.signup.signup.data

import androidx.lifecycle.MutableLiveData
import com.example.every.base.BaseViewModel

class WorkerSignUpViewModel : BaseViewModel(){

    val workerName = MutableLiveData<String>()
    val workerName_check = MutableLiveData<String>()

    fun checkType(text : String) : Boolean{
        if(text.indexOf(" ") >= 0){
            workerName_check.value = "근무 직장의 이름에 공백을 두지 마십시오."
            return false
        } else{
            workerName_check.value = null
            return true
        }
    }
    fun next() = onNextEvent.call()
}