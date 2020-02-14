package com.example.every.viewmodel.signin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.every.network.Data
import com.example.every.network.NetRetrofit
import com.example.every.network.request.model.signin.SignInData
import com.example.every.network.Response
import com.example.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class SignInViewModel : ViewModel() {

    private val netRetrofit = NetRetrofit()

    val token = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    val onSignUpEvent = SingleLiveEvent<Unit>()
    val onLostPwEvent = SingleLiveEvent<Unit>()
    val onSuccessEvent = SingleLiveEvent<Unit>()
    val onErrorEvent = SingleLiveEvent<Unit>()
    val onFailEvent = SingleLiveEvent<Unit>()

    fun signUp() = onSignUpEvent.call()
    fun lostPw() = onLostPwEvent.call()
    fun login(){
            val signInData = SignInData(email.value.toString().trim(), pw.value.toString().trim())
            val res : Call<Response<Data>> = netRetrofit.signIn.postSignIn(signInData)
            res.enqueue(object : Callback<Response<Data>>{
                override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                    when(response.code()){
                        200 -> {
                            token.value = response.body()!!.data!!.token
                            onSuccessEvent.call()
                        } 400 -> onErrorEvent.call() // 검증 오류
                          401 -> onFailEvent.call() // 인증 실패
                    }
                }
                override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                    Log.e("SignInViewModel[Error]", "서버와 통신을 실패하였습니다.")
                }
            })
    }
}