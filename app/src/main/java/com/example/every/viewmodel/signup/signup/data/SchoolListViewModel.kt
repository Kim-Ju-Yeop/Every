package com.example.every.viewmodel.signup.signup.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.every.network.NetRetrofit

class SchoolListViewModel : ViewModel(){

    val neRetrofit = NetRetrofit()
    val schoolName = MutableLiveData<String>()

    fun onSearch(){
        val res : Call<Response<Data>>
    }
}