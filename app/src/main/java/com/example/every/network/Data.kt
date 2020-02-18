package com.example.every.network

import com.example.every.DTO.SchoolDataList
import com.google.gson.annotations.SerializedName

class Data {

    var schools : List<SchoolDataList>? = null

    @SerializedName("x-access-token")
    lateinit var token : String

    @SerializedName("is_worker")
    var worker : Boolean = false

    @SerializedName("is_student")
    var student : Boolean = false
}