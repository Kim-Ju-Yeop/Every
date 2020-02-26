package com.example.every.viewmodel.signup.signup.data

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.example.every.base.BaseViewModel
import com.example.every.widget.SingleLiveEvent

class SchoolSignUpViewModel : BaseViewModel(){

    val schoolName = MutableLiveData<String>()
    val schoolId = MutableLiveData<String>()

    val onSearchEvent = SingleLiveEvent<Unit>()
    val onEnableTrueEVent = SingleLiveEvent<Unit>()
    val onEnableFalseEvent = SingleLiveEvent<Unit>()

    fun searchSchool() = onSearchEvent.call()
    fun schoolNameSetting(schoolData : SharedPreferences){
        if(!schoolData.getString("school_name", null).isNullOrEmpty()){
            onEnableTrueEVent.call()
            schoolName.value = schoolData.getString("school_name", "Error")
            schoolId.value = schoolData.getString("school_id", "Error")
        } else{
            onEnableFalseEvent.call()
            schoolName.value = "왼쪽 버튼을 눌러 검색을 해보세요!"
            schoolId.value = null
        }
    }
    fun next() = onNextEvent.call()
}