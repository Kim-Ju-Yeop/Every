package com.example.every.viewmodel.signup.signup.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.every.activity.signup.SignUpActivity
import com.example.every.viewmodel.base.BaseViewModel
import com.example.every.widget.SingleLiveEvent
import java.util.regex.Pattern

class Name_BirthViewModel : BaseViewModel() {

    val name = MutableLiveData<String>()
    val birth = MutableLiveData<String>()
    val birth_check = MutableLiveData<String>()

    fun next(){
        onSuccessEvent.call()
    }

    fun checkType(text : String) : Boolean{
        if(!birth_validity.matcher(text).matches()){
            birth_check.value = "태어난 년도를 4자리로 설정해주세요."
            return false
        } else{
            birth_check.value = null
            return true
        }
    }
}