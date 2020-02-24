package com.example.every.base.viewmodel.student

import androidx.lifecycle.ViewModel
import com.example.every.network.NetRetrofit
import com.example.every.widget.SingleLiveEvent

open class BaseStudentViewModel : ViewModel(){

    val netRetrofit = NetRetrofit()

    val onSuccessEvent = SingleLiveEvent<Unit>()
    val onFailEvent = SingleLiveEvent<Unit>()
    val onTokenEvent = SingleLiveEvent<Unit>()
}