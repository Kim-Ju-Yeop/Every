package com.example.every.viewmodel.base.student

import androidx.lifecycle.ViewModel
import com.example.every.network.NetRetrofit

open class BaseStudentFragmentViewModel : ViewModel(){

    val netRetrofit = NetRetrofit()
}