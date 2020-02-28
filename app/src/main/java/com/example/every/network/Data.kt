package com.example.every.network

import com.example.every.DTO.student.BambooPostList
import com.example.every.DTO.signup.SchoolDataList
import com.example.every.DTO.student.BambooMember
import com.example.every.DTO.student.BambooReplyList
import com.google.gson.annotations.SerializedName

class Data {

    var schools : List<SchoolDataList>? = null
    var posts : List<BambooPostList>? = null
    var replies : List<BambooReplyList>? = null
    val member : BambooMember? = null

    @SerializedName("x-access-token")
    lateinit var token : String

    @SerializedName("worker_idx")
    var worker_idx : Int? = null

    @SerializedName("student_idx")
    var student_idx : Int? = null
}