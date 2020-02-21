package com.example.every.viewmodel.student.bamboo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.every.fragment.base.tokenData
import com.example.every.network.Data
import com.example.every.network.Response
import com.example.every.network.request.model.student.BambooPostData
import com.example.every.viewmodel.base.student.BaseStudentFragmentViewModel
import retrofit2.Call
import retrofit2.Callback

class BambooPostViewModel : BaseStudentFragmentViewModel(){

    val content_EditText = MutableLiveData<String>()

    fun postBamboo(){
        if(!content_EditText.value.toString().isNullOrEmpty()){
            val bambooPostData = BambooPostData(content_EditText.value.toString())
            val res : Call<Response<Data>> = netRetrofit.bamboo.postBamboo(tokenData.token.value.toString(), bambooPostData)
            res.enqueue(object : Callback<Response<Data>>{
                override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {

                }
                override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                    println("서버 통신 X")
                }
            })
        }else{
            println("데이터 입력하지 앟")
        }
    }
}