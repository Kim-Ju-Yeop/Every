package com.example.every.viewmodel.signup.signup.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.every.widget.SingleLiveEvent
import java.util.regex.Pattern

class Email_PasswordViewModel : ViewModel(){

    val email = MutableLiveData<String>()
    val email_check = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val pw_check = MutableLiveData<String>()

    val onSuccessEvent = SingleLiveEvent<Unit>()
    val onFailEvent = SingleLiveEvent<Unit>()

    fun next(){
        if(checkEmpty(email.value.toString(), 0) && checkEmpty(pw.value.toString(), 1) && checkType(email.value.toString(), 0) && checkType(pw.value.toString(), 1)){
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
            if(id == 0) email_check.value = null
            else pw_check.value = null
            return true
        }
    }

    fun allNull(){
        pw.value = null
        email.value = null
        pw_check.value = null
        email_check.value = null
    }
}