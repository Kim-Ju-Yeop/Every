package com.project.every.viewmodel.signup.signup.data

import androidx.lifecycle.MutableLiveData
import com.project.every.base.BaseViewModel
import com.project.every.widget.SingleLiveEvent

class WorkerSignUpViewModel : BaseViewModel(){

    // MutableLiveData
    val workerName = MutableLiveData<String>()
    val workerName_check = MutableLiveData<String>()

    // SingleLiveEvent
    val onWorkerNextEvent = SingleLiveEvent<Unit>()

    fun checkType(text : String) : Boolean{
        if(text.indexOf(" ") >= 0){
            workerName_check.value = "근무 직장의 이름에 공백을 두지 마십시오."
            return false
        } else{
            workerName_check.value = null
            return true
        }
    } fun next() = onWorkerNextEvent.call()
}