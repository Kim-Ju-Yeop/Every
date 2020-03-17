package com.example.every.viewmodel.signin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.every.base.BaseViewModel
import com.example.every.network.Data
import com.example.every.network.request.model.signin.SignInData
import com.example.every.network.Response
import com.example.every.network.SHA512
import com.example.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class SignInViewModel : BaseViewModel() {

    /**
     * SignIn 로그인 API Response
     * status[200] -> 로그인 성공 : onLoginSuccessEvent
     * status[400] -> 검증 오류 : onLoginErrorEvent
     * status[401] -> 로그인 실패 : onFailureEvent
     */

    // MutableLiveData
    val token = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val worker_idx = MutableLiveData<Int>()
    val student_idx = MutableLiveData<Int>()

    // SingleLiveEvent
    val onLoginSuccessEvent = SingleLiveEvent<Unit>()
    val onLoginErrorEvent = SingleLiveEvent<Unit>()
    val onLoginFailureEvent = SingleLiveEvent<Unit>()
    val onSignUpEvent = SingleLiveEvent<Unit>()
    val onLostPwEvent = SingleLiveEvent<Unit>()

    fun postSignIn(){
            val signInData = SignInData(email.value.toString().trim(), SHA512.getSH512(pw.value.toString().trim())!!)
            val res : Call<Response<Data>> = netRetrofit.signIn.postSignIn(signInData)
            res.enqueue(object : Callback<Response<Data>>{
                override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                    when(response.code()){
                        200 -> {
                            token.value = response.body()!!.data!!.token

                            if(response.body()!!.data!!.worker_idx != null) worker_idx.value = response.body()!!.data!!.worker_idx
                            else if(response.body()!!.data!!.student_idx != null) student_idx.value = response.body()!!.data!!.student_idx

                            onLoginSuccessEvent.call()
                        } 400 -> onLoginErrorEvent.call()
                          401 -> onLoginFailureEvent.call()
                    }
                }
                override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                    Log.e("postSignIn[Error]", "로그인 과정에서 서버와 연결하지 못하였습니다.")
                }
            })
    }
    fun signUp() = onSignUpEvent.call()
    fun lostPw() = onLostPwEvent.call()
}