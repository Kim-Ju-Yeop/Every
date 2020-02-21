package com.example.every.viewmodel.base.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.every.network.NetRetrofit
import com.example.every.widget.SingleLiveEvent
import java.util.regex.Pattern

open class BaseSignUpViewModel : ViewModel(){

    // NetRetrofit
    val netRetrofit = NetRetrofit()

    // SingleLiveEvent
    val onSuccessEvent = SingleLiveEvent<Unit>()
    val onFailEvent = SingleLiveEvent<Unit>()

    // Validity : 유효성
    val password_validity = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{8,20}$")
    val birth_validity = Pattern.compile("^[0-9]{4,4}\$")
    val phone_validity = Pattern.compile("^01(?:0|1|[6-9])[-]?(?:\\d{4})[-]?\\d{4}$")
}