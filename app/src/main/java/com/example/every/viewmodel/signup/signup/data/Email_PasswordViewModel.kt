package com.example.every.viewmodel.signup.signup.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.every.activity.signup.SignUpActivity
import com.example.every.activity.signup.signup.data.Email_PasswordActivity
import com.example.every.network.Data
import com.example.every.network.NetRetrofit
import com.example.every.network.Response
import com.example.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import java.util.regex.Pattern

class Email_PasswordViewModel : ViewModel(){

    val neRetrofit = NetRetrofit()

    val email = MutableLiveData<String>()
    val email_check = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val pw_check = MutableLiveData<String>()

    val onSuccessEvent = SingleLiveEvent<Unit>()
    val onFailEvent = SingleLiveEvent<Unit>()

    val check = MutableLiveData<Boolean>()

    fun next(){
        if(checkEmpty(email.value.toString(), 0) && checkEmpty(pw.value.toString(), 1) && checkType(email.value.toString(), 0) && checkType(pw.value.toString(), 1) && check.value == true){
            onSuccessEvent.call()
        } else{
            allNull()
            onFailEvent.call()
        }
    }

    fun checkEmpty(text : String, id : Int) : Boolean{
        if(text.isNullOrEmpty() && id == 0){
            email_check.value = "이메일을 입력하지 않으셨습니다."
            return false
        } else if(text.isNullOrEmpty() && id == 1){
            pw_check.value = "비밀번호를 입력하지 않으셨습니다."
            return false
        } else{
            if(id == 0) email_check.value = null
            else pw_check.value = null
            return true
        }
    }

    fun checkType(text : String, id : Int) : Boolean{
        if(id == 0 && !android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()){
            email_check.value = "이메일 형식이 올바르지 않습니다."
            return false
        } else if(id == 1 && !Pattern.matches("^[a-zA-Z0-9!@.#$%^&*?_~]{8,20}$", pw.value.toString())){
            pw_check.value = "비밀번호 형식이 올바르지 않습니다."
            return false
        }
        else{
            if(id == 0){
                email_check.value = null
                overlapEmail(email.value.toString())
            }
            else pw_check.value = null
            return true
        }
    }

    fun overlapEmail(text : String) : Boolean{
        val res : Call<Response<Data>> = neRetrofit.signUp.getEmailOverlap(text)
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                if(response.code() == 200) check.value = true
                else if(response.code() == 409){
                    check.value = false
                    email_check.value = "중복된 이메일이 이미 존재합니다."
                }
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                check.value = false
                Log.e("overlapEmail[Error]", "이메일 중복확인 과정에서 서버와 통신이 되지 않습니다.")
            }
        })
        return true
    }

    fun allNull(){
        pw.value = null
        email.value = null
        pw_check.value = null
        email_check.value = null
    }
}