package com.example.every.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetRetrofit{
    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://ec2-13-209-17-179.ap-northeast-2.compute.amazonaws.com:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

//    val school : School = retrofit.create(School::class.java)
//    val meals : Meals = retrofit.create(Meals::class.java)*/

    companion object{
        val instance = NetRetrofit()
    }
}