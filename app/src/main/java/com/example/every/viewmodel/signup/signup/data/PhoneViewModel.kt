package com.example.every.viewmodel.signup.signup.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.every.network.Data
import com.example.every.network.NetRetrofit
import com.example.every.network.Response
import com.example.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import java.util.regex.Pattern

class PhoneViewModel : ViewModel(){

    val neRetrofit = NetRetrofit()

    val phone = MutableLiveData<String>()
    val phone_check = MutableLiveData<String>()

    val onSuccessEvent = SingleLiveEvent<Unit>()
    val onFailEvent = SingleLiveEvent<Unit>()

    val check = MutableLiveData<Boolean>()

    fun next(){
        if(checkEmpty(phone.value.toString()) && checkType(phone.value.toString()) && check.value == true){
            onSuccessEvent.call()
        } else{
            allNull()
            onFailEvent.call()
        }
    }
    fun checkEmpty(text : String) : Boolean{
        if(text.isNullOrEmpty()){
            phone_check.value = "전화번호를 입력하지 않으셨습니다."
            return false
        }else return true
    }
    fun checkType(text : String) : Boolean{
        if(!Pattern.matches("^01(?:0|1|[6-9])[-]?(?:\\d{4})[-]?\\d{4}$", text)){
            phone_check.value = "전화번호의 형식이 올바르지 않습니다."
            return false
        }else {
            phone_check.value = null
            overlapPhone(phone.value.toString())
            return true
        }
    }
    fun overlapPhone(text :String){
        val res : Call<Response<Data>> = neRetrofit.signUp.getPhoneOverlap(text)
        res.enqueue(object : Callback<Response<Data>> {
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                if(response.code() == 200) check.value = true
                else if(response.code() == 409){
                    check.value = false
                    phone_check.value = "중복된 전화번호가 이미 존재합니다."
                }
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                check.value = false
                Log.e("overlapEmail[Error]", "전화번호 중복확인 과정에서 서버와 통신이 되지 않습니다.")
            }
        })
    }
    fun allNull(){
        phone.value = null
    }
}