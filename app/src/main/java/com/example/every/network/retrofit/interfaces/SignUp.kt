package com.example.every.network.retrofit.interfaces

import com.example.every.network.Data
import com.example.every.network.request.model.signup.SignUpDataStudent
import com.example.every.network.request.model.signup.SignUpDataWorker
import com.example.every.network.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUp {
    @POST("auth/register/student")
    fun postSignUpStudent(@Body signUpDataStudent : SignUpDataStudent) : Call<Response<Data>>

    @POST("auth/register/worker")
    fun postSignUpWorker(@Body signUpDataWorker : SignUpDataWorker) : Call<Response<Data>>
}