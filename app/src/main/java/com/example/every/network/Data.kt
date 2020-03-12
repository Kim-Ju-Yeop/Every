package com.example.every.network

import com.example.every.DTO.student.bamboo.BambooPostList
import com.example.every.DTO.signup.SchoolDataList
import com.example.every.DTO.student.bamboo.BambooMember
import com.example.every.DTO.student.bamboo.BambooReplyList
import com.example.every.DTO.student.home.MealsList
import com.example.every.DTO.student.schedule.SchedulesList
import com.google.gson.annotations.SerializedName

class Data {

    // SignUp
    var schools : List<SchoolDataList>? = null

    // Bamboo
    var posts : List<BambooPostList>? = null
    var replies : List<BambooReplyList>? = null
    var member : BambooMember? = null

    // Home
    var meals : List<MealsList>? = null

    // Schedule
    var schedules : List<SchedulesList>? = null
    var schedule : SchedulesList? = null

    @SerializedName("x-access-token")
    lateinit var token : String

    @SerializedName("worker_idx")
    var worker_idx : Int? = null

    @SerializedName("student_idx")
    var student_idx : Int? = null
}