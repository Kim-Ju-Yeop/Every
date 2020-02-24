package com.example.every.base.view.student

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData

object tokenData{
    val token = MutableLiveData<String>()
}

open class BaseStudentFragment : Fragment(){

}

open class BaseStudentActivity : AppCompatActivity(){

}