package com.project.every.network.retrofit.interfaces.signup

import com.project.every.network.Data
import com.project.every.network.request.model.signup.SignUpDataStudent
import com.project.every.network.request.model.signup.SignUpDataWorker
import com.project.every.network.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SignUp {
    @POST("auth/register/student")
    fun postSignUpStudent(@Body signUpDataStudent : SignUpDataStudent) : Call<Response<Data>>

    @POST("auth/register/worker")
    fun postSignUpWorker(@Body signUpDataWorker : SignUpDataWorker) : Call<Response<Data>>

    @GET("auth/check/email")
    fun getOverlapEmail(@Query("email") email : String) : Call<Response<Data>>

    @GET("auth/check/phone")
    fun getOverlapPhone(@Query("phone") phone : String) : Call<Response<Data>>

    @GET("school")
    fun getSchool(@Query("query") schoolName : String) : Call<Response<Data>>
}