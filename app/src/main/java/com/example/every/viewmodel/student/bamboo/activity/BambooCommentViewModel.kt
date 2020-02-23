package com.example.every.viewmodel.student.bamboo.activity

import androidx.lifecycle.ViewModel
import com.example.every.fragment.base.tokenData
import com.example.every.network.Data
import com.example.every.network.NetRetrofit
import com.example.every.network.Response
import retrofit2.Call
import retrofit2.Callback

class BambooCommentViewModel : ViewModel(){

    val netRetrofit = NetRetrofit()

    fun getBambooComment(idx : Int){
        val res : Call<Response<Data>> = netRetrofit.bamboo.getBambooComment(tokenData.token.value.toString(), idx)
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                println()
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                println()
            }
        })
    }
}