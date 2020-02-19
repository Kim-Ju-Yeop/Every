package com.example.every.network.retrofit.interfaces.signin

import com.example.every.network.Data
import com.example.every.network.request.model.signin.SignInData
import com.example.every.network.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignIn {
    @POST("auth/login")
    fun postSignIn(@Body signInData : SignInData) : Call<Response<Data>>
}