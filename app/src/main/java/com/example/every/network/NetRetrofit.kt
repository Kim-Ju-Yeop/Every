package com.example.every.network

import com.example.every.network.retrofit.interfaces.SignIn
import com.example.every.network.retrofit.interfaces.SignUp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetRetrofit{
    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://54.180.109.187:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val signIn : SignIn = retrofit.create(SignIn::class.java)
    val signUp : SignUp = retrofit.create(SignUp::class.java)

    companion object{
        val instance = NetRetrofit()
    }
}