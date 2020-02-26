package com.example.every.viewmodel.signup.signup.data

import androidx.lifecycle.MutableLiveData
import com.example.every.base.BaseViewModel
import java.util.regex.Pattern

class Name_BirthSignUpViewModel : BaseViewModel() {

    val birth = MutableLiveData<String>()
    val birth_check = MutableLiveData<String>()

    fun checkType(text : String) : Boolean{
        if(!Pattern.compile("^[0-9]{4,4}\$").matcher(text).matches()){
            birth_check.value = "태어난 년도를 4자리로 설정해주세요."
            return false
        } else{
            birth_check.value = null
            return true
        }
    }
    fun next() = onNextEvent.call()
}