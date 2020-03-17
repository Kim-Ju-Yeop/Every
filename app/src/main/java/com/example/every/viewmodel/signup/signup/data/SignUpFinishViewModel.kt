package com.example.every.viewmodel.signup.signup.data

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.every.base.BaseViewModel
import com.example.every.base.SignUpData
import com.example.every.network.Data
import com.example.every.network.Response
import com.example.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class SignUpFinishViewModel : BaseViewModel() {

    /**
     * postSignUp 회원가입 API Response (학생, 직장인)
     * status[201] 회원 가입 성공 : onSignUpSuccessEvent
     * status[400] 검증 오류 : onSignUpFailureEvent
     */

    // MutableLiveData
    val firstText = MutableLiveData<String>()
    val secondText = MutableLiveData<String>()

    // SingleLiveEvent
    val onSignUpSuccessEvent = SingleLiveEvent<Unit>()
    val onSignUpFailureEvent = SingleLiveEvent<Unit>()
    val onSignUpNextEvent = SingleLiveEvent<Unit>()

    fun postSignUp(identity : Int){
        if(identity == 0){
            val res : Call<Response<Data>> = netRetrofit.signUp.postSignUpStudent(SignUpData.signUpDataStudent)
            Log.e("test", SignUpData.signUpDataStudent.pw.toString())
            res.enqueue(object : Callback<Response<Data>>{
                override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                    when(response.code()){
                        201 ->{
                            firstText.value = "회원가입 완료"
                            secondText.value = "정상적으로 회원가입이 완료되었습니다!"
                            onSignUpSuccessEvent.call()
                        }
                        400 ->{
                            firstText.value = "회원가입 실패"
                            secondText.value = "회원가입을 수행하지 못하였습니다!"
                            onSignUpFailureEvent.call()
                        }
                    }
                }
                @SuppressLint("LongLogTag")
                override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                    Log.e("postSignUpStudent[Error]", "학생용 회원가입 과정에서 서버와 통신이 되지 않습니다.")
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
                            onSignUpSuccessEvent.call()
                        }
                        400 ->{
                            firstText.value = "회원가입 실패"
                            secondText.value = "회원가입을 수행하지 못하였습니다!"
                            onSignUpFailureEvent.call()
                        }
                    }
                }
                override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                    Log.e("postSignUpWorker[Error]", "직장인 회원가입 과정에서 서버와 통신이 되지 않습니다.")
                }
            })
        }
    } fun next() = onSignUpNextEvent.call()
}