package com.example.every.viewmodel.signup.signup.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.every.widget.SingleLiveEvent
import java.util.regex.Pattern

class PhoneViewModel : ViewModel(){

    val phone = MutableLiveData<String>()

    val onSuccessEvent = SingleLiveEvent<Unit>()
    val onFailEvent = SingleLiveEvent<Unit>()

    fun next(){
        if(checkEmpty(phone.value.toString()) && checkType(phone.value.toString())){
            onSuccessEvent.call()
        } else{
            allNull()
            onFailEvent.call()
        }
    }
    fun checkEmpty(text : String) : Boolean{
        return if (text.isNullOrEmpty()) false else true
    }
    fun checkType(text : String) : Boolean{
        return if(!Pattern.matches("^01(?:0|1|[6-9])[-]?(?:\\d{4})[-]?\\d{4}$", text)) false else true
    }
    fun allNull(){
        phone.value = null
    }
}