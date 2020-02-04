package com.example.every.viewmodel.signup.signup.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.every.activity.signup.SignUpData
import com.example.every.network.Data
import com.example.every.network.Response
import com.example.every.viewmodel.base.BaseViewModel
import com.example.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class SignUpFinishViewModel : BaseViewModel(){

    val firstText = MutableLiveData<String>()
    val SecondText = MutableLiveData<String>()

    val onButtonClickEvent = SingleLiveEvent<Unit>()

    fun signUp(identity : Int){
        if(identity == 0){
            val res : Call<Response<Data>> = netRetrofit.signUp.postSignUpStudent(SignUpData.signUpDataStudent)
            res.enqueue(object : Callback<Response<Data>>{
                override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                    when(response.code()){
                        201 ->{
                            firstText.value = "회원가입 성공"
                            SecondText.value = "이제 마음 껏 Every를 즐겨보세요!"
                            onSuccessEvent.call()
                        }
                        400 ->{
                            firstText.value = "회원가입 실패"
                            SecondText.value = "다시 회원가입을 진행해주세요!"
                            onFailEvent.call()
                        }
                    }
                }
                override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                    Log.e("signUpStudent[Error]", "학생용 회원가입 과정에서 서버와 통신이 되지 않습니다.")
                }
            })
        }else if(identity == 1){
            val res : Call<Response<Data>> = netRetrofit.signUp.postSignUpWorker(SignUpData.signUpDataWorker)
            res.enqueue(object : Callback<Response<Data>>{
                override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                    when(response.code()){
                        201 ->{
                            firstText.value = "회원가입 성공"
                            SecondText.value = "이제 마음 껏 Every를 즐겨보세요!"
                            onSuccessEvent.call()
                        }
                        400 ->{
                            firstText.value = "회원가입 실패"
                            SecondText.value = "다시 회원가입을 진행해주세요!"
                            onFailEvent.call()
                        }
                    }
                }
                override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                    Log.e("signUpWorker[Error]", "직장인 회원가입 과정에서 서버와 통신이 되지 않습니다.")
                }
            })
        }
    }
    fun next() = onButtonClickEvent.call()
}