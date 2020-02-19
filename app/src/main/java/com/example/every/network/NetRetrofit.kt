package com.example.every.network

import com.example.every.network.retrofit.interfaces.signin.SignIn
import com.example.every.network.retrofit.interfaces.signup.SignUp
import com.example.every.network.retrofit.interfaces.student.Bamboo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetRetrofit{
    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://54.180.109.187:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val signIn : SignIn = retrofit.create(SignIn::class.java)
    val signUp : SignUp = retrofit.create(SignUp::class.java)
    val bamboo : Bamboo = retrofit.create(Bamboo::class.java)

    companion object{
        val instance = NetRetrofit()
    }
}