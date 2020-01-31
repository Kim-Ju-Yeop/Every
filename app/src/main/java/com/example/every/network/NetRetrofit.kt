package com.example.every.network

import com.example.every.network.retrofit.interfaces.SignIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetRetrofit{
    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://ec2-13-209-17-179.ap-northeast-2.compute.amazonaws.com:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val signIn : SignIn = retrofit.create(SignIn::class.java)

    companion object{
        val instance = NetRetrofit()
    }
}