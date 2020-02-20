package com.example.every.network.retrofit.interfaces.student

import com.example.every.network.Data
import com.example.every.network.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Bamboo {

    @GET("bamboo/post")
    fun getBambooPost(@Header("token") token : String) : Call<Response<Data>>

    @GET("bamboo/reply")
    fun getCommentList(@Header("token") token : String, @Query("post") post : Int) : Call<Response<Data>>
}