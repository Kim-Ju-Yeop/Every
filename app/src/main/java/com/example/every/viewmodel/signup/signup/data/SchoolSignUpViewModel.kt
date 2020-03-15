package com.example.every.viewmodel.signup.signup.data

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.example.every.base.BaseViewModel
import com.example.every.widget.SingleLiveEvent

class SchoolSignUpViewModel : BaseViewModel(){

    // MutableLiveData
    val schoolName = MutableLiveData<String>()
    val schoolId = MutableLiveData<String>()

    val onSchoolSearchEvent = SingleLiveEvent<Unit>()
    val onSchoolEnableTrueEVent = SingleLiveEvent<Unit>()
    val onSchoolEnableFalseEvent = SingleLiveEvent<Unit>()
    val onSchoolNextEvent = SingleLiveEvent<Unit>()

    fun schoolNameSetting(schoolData : SharedPreferences){
        if(!schoolData.getString("school_name", null).isNullOrEmpty()){
            onSchoolEnableTrueEVent.call()
            schoolName.value = schoolData.getString("school_name", null)
            schoolId.value = schoolData.getString("school_id", null)
        } else{
            onSchoolEnableFalseEvent.call()
            schoolName.value = "학교를 검색해보세요!"
            schoolId.value = null
        }
    }
    fun next() = onSchoolNextEvent.call()
    fun searchSchool() = onSchoolSearchEvent.call()
}