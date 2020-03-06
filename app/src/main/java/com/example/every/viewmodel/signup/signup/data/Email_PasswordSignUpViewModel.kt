package com.example.every.viewmodel.signup.signup.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.every.base.BaseViewModel
import com.example.every.network.Data
import com.example.every.network.Response
import retrofit2.Call
import retrofit2.Callback
import java.util.regex.Pattern

class Email_PasswordSignUpViewModel : BaseViewModel(){

    /**
     * EmailOverlap 이메일 중복확인 API Response
     * status[200] -> 이메일 중복 없음
     * status[400] -> 이메일 검증 오류
     * status[409] -> 이메일 중복 확인
     */

    val email = MutableLiveData<String>()
    val email_check = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val pw_check = MutableLiveData<String>()

    fun checkType(text : String, id : Int) : Boolean{
        if(id == 0 && !android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()){
            email_check.value = "이메일 형식이 올바르지 않습니다."
            return false
        } else if(id == 1 && !Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{8,20}$").matcher(text).matches()){
            pw_check.value = "비밀번호 형식이 올바르지 않습니다."
            return false
        }
        else{
            if(id == 0) email_check.value = null
            else pw_check.value = null
            return true
        }
    }

    fun overlapEmail(text : String){
        val res : Call<Response<Data>> = netRetrofit.signUp.getOverlapEmail(text)
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                when(response.code()){
                    200 -> email_check.value = null
                    400 -> email_check.value = "이메일 형식이 올바르지 않습니다."
                    409 -> email_check.value = "중복된 이메일이 이미 존재합니다."
                }
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("overlapEmail[Error]", "이메일 중복확인 과정에서 서버와 통신이 되지 않습니다.")
            }
        })
    } fun next() = onNextEvent.call()
}