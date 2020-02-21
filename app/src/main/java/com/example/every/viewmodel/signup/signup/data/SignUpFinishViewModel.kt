package com.example.every.viewmodel.signup.signup.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.every.activity.signup.SignUpData
import com.example.every.network.Data
import com.example.every.network.Response
import com.example.every.viewmodel.base.signup.BaseSignUpViewModel
import com.example.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class SignUpFinishViewModel : BaseSignUpViewModel(){

    /**
     * SignUp 회원가입 API Response (학생, 직장인)
     * status[201] 회원 가입 성공
     * status[400] 검증 오류
     */

    val firstText = MutableLiveData<String>()
    val secondText = MutableLiveData<String>()

    fun signUp(identity : Int){
        if(identity == 0){
            val res : Call<Response<Data>> = netRetrofit.signUp.postSignUpStudent(SignUpData.signUpDataStudent)
            res.enqueue(object : Callback<Response<Data>>{
                override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                    when(response.code()){
                        201 ->{
                            firstText.value = "회원가입 완료"
                            secondText.value = "정상적으로 회원가입이 완료되었습니다!"
                            onSuccessEvent.call()
                        }
                        400 ->{
                            firstText.value = "회원가입 실패"
                            secondText.value = "회원가입을 수행하지 못하였습니다!"
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
                            firstText.value = "회원가입 완료"
                            secondText.value = "정상적으로 회원가입이 완료되었습니다!"
                            onSuccessEvent.call()
                        }
                        400 ->{
                            firstText.value = "회원가입 실패"
                            secondText.value = "회원가입을 수행하지 못하였습니다!"
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
    fun next() = onNextEvent.call()
}