package com.example.every.viewmodel.signup.signup.data

import androidx.lifecycle.MutableLiveData
import com.example.every.viewmodel.base.signup.BaseSignUpViewModel

class Name_BirthSignUpViewModel : BaseSignUpViewModel() {

    val birth = MutableLiveData<String>()
    val birth_check = MutableLiveData<String>()

    fun checkType(text : String) : Boolean{
        if(!birth_validity.matcher(text).matches()){
            birth_check.value = "태어난 년도를 4자리로 설정해주세요."
            return false
        } else{
            birth_check.value = null
            return true
        }
    }
    fun next() = onSuccessEvent.call()
}