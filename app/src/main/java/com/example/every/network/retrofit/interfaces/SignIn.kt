package com.example.every.network.retrofit.interfaces

import com.example.every.network.Data
import com.example.every.network.request.model.LoginData
import com.example.every.network.response.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignIn {
    @POST("auth/login")
    fun postSignIn(@Body loginData : LoginData) : Call<Response<Data>>
}