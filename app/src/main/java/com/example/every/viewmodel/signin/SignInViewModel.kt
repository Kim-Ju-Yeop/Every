package com.example.every.viewmodel.signin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.every.network.Data
import com.example.every.network.NetRetrofit
import com.example.every.network.request.model.LoginData
import com.example.every.network.response.Response
import com.example.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class SignInViewModel : ViewModel() {

    val neRetrofit = NetRetrofit()
    val email = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    val onSignUpEvent = SingleLiveEvent<Unit>()
    val onLostPwEvent = SingleLiveEvent<Unit>()
    val onEmptyEvent = SingleLiveEvent<Unit>()
    val onSuccessEvent = SingleLiveEvent<Unit>()
    val onErrorEvent = SingleLiveEvent<Unit>()
    val onFailEvent = SingleLiveEvent<Unit>()
    val onConnectErrorEvent = SingleLiveEvent<Unit>()

    val token = MutableLiveData<String>()

    fun signUp() = onSignUpEvent.call()
    fun lostPw() = onLostPwEvent.call()
    fun login(){
        if(checkEmpty(email, pw)){
            val loginData = LoginData(email.value.toString().trim(), pw.value.toString().trim())
            val res : Call<Response<Data>> = neRetrofit.signIn.postSignIn(loginData)
            res.enqueue(object : Callback<Response<Data>>{
                override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                    when(response.code()){
                        200 -> {
                            token.value = response.body()!!.data!!.token
                            onSuccessEvent.call()
                        } 400 -> onErrorEvent.call()
                          401 -> onFailEvent.call()
                    }
                }
                override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                    onConnectErrorEvent.call()
                }
            })
        }else onEmptyEvent.call()
    }
    fun checkEmpty(email : MutableLiveData<String>, pw : MutableLiveData<String>) : Boolean{
        return if(email.value.isNullOrEmpty() || pw.value.isNullOrEmpty()) false else true
    }
}