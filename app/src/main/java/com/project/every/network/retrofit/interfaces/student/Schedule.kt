package com.project.every.network.retrofit.interfaces.student

import com.project.every.network.Data
import com.project.every.network.Response
import com.project.every.network.request.model.student.schedule.SchedulePostData
import retrofit2.Call
import retrofit2.http.*

interface Schedule {

    @GET("schedule")
    fun getSchedule(@Header("token") token : String) : Call<Response<Data>>

    @POST("schedule")
    fun postSchedule(@Header("token") token : String, @Body schedulePostData : SchedulePostData) : Call<Response<Data>>

    @GET("schedule/{idx}")
    fun getIdxSchedule(@Header("token") token : String, @Path("idx") idx : Int) : Call<Response<Data>>

    @DELETE("schedule/{idx}")
    fun deleteSchedule(@Header("token") token : String, @Path("idx") idx : Int) : Call<Response<Data>>

    @PUT("schedule/{idx}")
    fun editSchedule(@Header("token") token : String, @Body schedulePostData : SchedulePostData, @Path("idx") idx : Int) : Call<Response<Data>>
}