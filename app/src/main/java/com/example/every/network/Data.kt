package com.example.every.network

import com.google.gson.annotations.SerializedName

class Data {
//    var schoolList : List<SearchSchool>? = null
//    var meal : List<String> ?= null

    @SerializedName("x-access-token")
    lateinit var token : String
}