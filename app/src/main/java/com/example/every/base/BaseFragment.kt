package com.example.every.base

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData

object StudentData{
    val token = MutableLiveData<String>()
    val postIdx = MutableLiveData<Int>()
}

abstract class BaseFragment : Fragment(){

    abstract fun observerViewModel()

    fun toastMessage(context : Context, message : String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}