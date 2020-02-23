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

    @SerializedName("is_worker")
    var worker : Boolean = false

    @SerializedName("is_student")
    var student : Boolean = false
}