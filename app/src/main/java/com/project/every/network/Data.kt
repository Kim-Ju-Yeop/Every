package com.project.every.network

import com.project.every.DTO.student.bamboo.BambooPostList
import com.project.every.DTO.signup.SchoolDataList
import com.project.every.DTO.student.bamboo.BambooMember
import com.project.every.DTO.student.bamboo.BambooReplyList
import com.project.every.DTO.student.home.MealsList
import com.project.every.DTO.student.schedule.SchedulesList
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

    // More
    var school : SchoolDataList?= null

    @SerializedName("x-access-token")
    lateinit var token : String

    @SerializedName("worker_idx")
    var worker_idx : Int? = null

    @SerializedName("student_idx")
    var student_idx : Int? = null
}