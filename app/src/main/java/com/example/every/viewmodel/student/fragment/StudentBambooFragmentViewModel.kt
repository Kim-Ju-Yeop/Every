package com.example.every.viewmodel.student.fragment

import android.util.Log
import com.example.every.network.Data
import com.example.every.network.Response
import com.example.every.viewmodel.base.student.BaseStudentFragmentViewModel
import retrofit2.Call
import retrofit2.Callback

class StudentBambooFragmentViewModel : BaseStudentFragmentViewModel(){

    fun bambooPostList(token : String){
        val res : Call<Response<Data>> = netRetrofit.bamboo.getBambooPost(token)
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {

            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("test", "실패")
            }
        })
    }
}