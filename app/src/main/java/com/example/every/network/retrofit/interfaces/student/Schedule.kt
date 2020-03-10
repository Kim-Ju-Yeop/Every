package com.example.every.network.retrofit.interfaces.student

import com.example.every.network.Data
import com.example.every.network.Response
import com.example.every.network.request.model.student.schedule.SchedulePostData
import retrofit2.Call
import retrofit2.http.*

interface Schedule {

    @GET("schedule")
    fun getSchedule(@Header("token") token : String) : Call<Response<Data>>

    @POST("schedule")
    fun postSchedule(@Header("token") token : String, @Body schedulePostData : SchedulePostData) : Call<Response<Data>>

    @GET("schedule/{idx}")
    fun getIdxSchedule(@Header("token") token : String, @Path("idx") idx : Int) : Call<Response<Data>>
}