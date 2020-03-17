package com.project.every.viewmodel.signup

import androidx.lifecycle.MutableLiveData
import com.project.every.base.BaseViewModel
import com.project.every.widget.SingleLiveEvent

class SignUpViewModel : BaseViewModel(){

    val checkInfo = MutableLiveData<Int>()
    val onCheckEvent = SingleLiveEvent<Unit>()

    fun clickEvent(info : Int){
        checkInfo.value = info
        onCheckEvent.call()
    }
}