package com.example.every.viewmodel.signup

import androidx.lifecycle.MutableLiveData
import com.example.every.base.BaseViewModel
import com.example.every.widget.SingleLiveEvent

class SignUpViewModel : BaseViewModel(){

    // MutableLiveData
    val checkInfo = MutableLiveData<Int>()

    // SingleLiveEvent
    val onCheckEvent = SingleLiveEvent<Unit>()

    fun clickEvent(info : Int){
        checkInfo.value = info
        onCheckEvent.call()
    }
}