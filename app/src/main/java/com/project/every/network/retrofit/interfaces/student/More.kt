package com.project.every.network.retrofit.interfaces.student

import com.project.every.network.Data
import com.project.every.network.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface More {

    @GET("school/{school_id}")
    fun getSchoolInfo(@Path("school_id") school_id : String) : Call<Response<Data>>
}