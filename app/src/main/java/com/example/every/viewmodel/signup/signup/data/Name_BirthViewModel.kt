package com.example.every.viewmodel.signup.signup.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.every.activity.signup.SignUpActivity
import com.example.every.widget.SingleLiveEvent
import java.util.regex.Pattern

class Name_BirthViewModel : ViewModel() {

    val name = MutableLiveData<String>()
    val name_check = MutableLiveData<String>()
    val birth = MutableLiveData<String>()
    val birth_check = MutableLiveData<String>()

    val onSuccessEvent = SingleLiveEvent<Unit>()
    val onFailEvent = SingleLiveEvent<Unit>()

    val signUpActivity = SignUpActivity()

    fun next(){
        if(checkEmpty(name.value.toString(), 0) && checkEmpty(name.value.toString(), 1) && checkType(birth.value.toString())){
            onSuccessEvent.call()
        } else{
            allNull()
            onFailEvent.call()
        }
    }

    fun checkEmpty(text : String, id : Int) : Boolean{
        if(text.isNullOrEmpty() && id == 0){
            name_check.value = "이름을 입력하지 않으셨습니다."
            return false
        } else if(text.isNullOrEmpty() && id == 1){
            birth_check.value = "태어난 년도를 입력하지 않으셨습니다."
            return false
        } else{
            if(id == 0) name_check.value = null
            else birth_check.value = null
            return true
        }
    }

    fun checkType(text : String) : Boolean{
        if(!Pattern.matches("^[a-zA-Z0-9!@.#$%^&*?_~]{4,4}$", text)){
            birth_check.value = "태어난 년도를 4자리로 설정해주세요."
            return false
        }
        else{
            birth_check.value = null
            return true
        }
    }

    fun allNull(){
        name.value = null
        birth.value = null
        name_check.value = null
        birth_check.value = null
    }
}