package com.example.every.network.retrofit.interfaces

import com.example.every.network.Data
import com.example.every.network.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface School {
    @GET("school")
    fun searchSchool(@Query("query") schoolName : String) : Call<Response<Data>>
}