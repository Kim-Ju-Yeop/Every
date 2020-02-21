package com.example.every.fragment.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData

object tokenData{
    val token = MutableLiveData<String>()
}

open class BaseStudentFragment : Fragment(){

}