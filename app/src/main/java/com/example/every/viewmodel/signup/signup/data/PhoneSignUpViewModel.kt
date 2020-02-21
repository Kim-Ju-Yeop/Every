package com.example.every.viewmodel.signup.signup.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.every.network.Data
import com.example.every.network.Response
import com.example.every.viewmodel.base.signup.BaseSignUpViewModel
import com.example.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class PhoneSignUpViewModel : BaseSignUpViewModel(){

    /**
     * PhoneOverlap 전화번호 중복확인 API Response
     * status[200] -> 전화번호 중복 없음
     * status[400] -> 전화번호 검증 오류
     * status[409] -> 전화번호 중복 확인
     */

    val phone = MutableLiveData<String>()
    val phone_check = MutableLiveData<String>()

    fun checkType(text : String) : Boolean{
        if(!phone_validity.matcher(text).matches()){
            phone_check.value = "전화번호의 형식이 올바르지 않습니다."
            return false
        }else {
            phone_check.value = null
            return true
        }
    }
    fun overlapPhone(text :String){
        val res : Call<Response<Data>> = netRetrofit.signUp.getOverlapPhone(text)
        res.enqueue(object : Callback<Response<Data>> {
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                when(response.code()){
                    200 -> {
                        phone_check.value = null
                        onSuccessEvent.call()
                    }
                    400 -> phone_check.value = "전화번호의 형식이 올바르지 않습니다."
                    409 -> {
                        phone_check.value = "중복된 전화번호가 이미 존재합니다."
                        onFailEvent.call()
                    }
                }
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("overlapPhone[Error]", "전화번호 중복확인 과정에서 서버와 통신이 되지 않습니다.")
            }
        })
    }
    fun next() = onNextEvent.call()
}