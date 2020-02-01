package com.example.every.network

import com.example.every.DTO.SchoolDataList
import com.google.gson.annotations.SerializedName

class Data {

    var schoolList : List<SchoolDataList>? = null

    @SerializedName("x-access-token")
    lateinit var token : String
}