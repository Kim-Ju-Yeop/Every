package com.project.every.base

import androidx.lifecycle.ViewModel
import com.project.every.network.NetRetrofit
import com.project.every.widget.SingleLiveEvent

open class BaseViewModel : ViewModel(){

    // Server Connect
    val netRetrofit = NetRetrofit()

    // Server Response
    val onSuccessEvent = SingleLiveEvent<Unit>() // 성공 이벤트
    val onErrorEvent = SingleLiveEvent<Unit>() // 검증 오류 이벤트
    val onFailEvent = SingleLiveEvent<Unit>() // 실패 이벤트
    val onNextEvent = SingleLiveEvent<Unit>() // 클릭 이벤트
}