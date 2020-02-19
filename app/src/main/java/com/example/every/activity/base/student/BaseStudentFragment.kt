package com.example.every.activity.base.student

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData

object tokenData{
    val token = MutableLiveData<String>()
}

open class BaseStudentFragment : Fragment(){

}