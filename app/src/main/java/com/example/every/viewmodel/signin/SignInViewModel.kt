package com.example.every.viewmodel.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.every.widget.SingleLiveEvent

class SignInViewModel : ViewModel() {

    val email = MutableLiveData<Unit>()
    val pw = MutableLiveData<Unit>()

    val onSignUpEvent = SingleLiveEvent<Unit>()

    fun signUp() = onSignUpEvent.call()
}