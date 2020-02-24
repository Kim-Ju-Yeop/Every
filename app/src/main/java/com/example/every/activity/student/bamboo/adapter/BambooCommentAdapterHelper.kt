package com.example.every.activity.student.bamboo.adapter

import android.widget.TextView
import com.example.every.fragment.student.bamboo.adapter.TIME_MAXIMUM
import com.example.every.base.view.student.tokenData
import com.example.every.network.Data
import com.example.every.network.NetRetrofit
import com.example.every.network.Response
import retrofit2.Call
import retrofit2.Callback
import java.util.*

fun getStudentInfo(studentIdx : Int, studentName : TextView){
    val neRetrofit = NetRetrofit()
    val res : Call<Response<Data>> = neRetrofit.bamboo.getStudentInfo(tokenData.token.value.toString(), studentIdx)
    res.enqueue(object : Callback<Response<Data>>{
        override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
            when(response.code()){
                200 -> studentName.text = response.body()!!.data!!.member!!.name
                // 400 처리 해야함
            }
        }
        override fun onFailure(call: Call<Response<Data>>, t: Throwable) {

        }
    })
}

fun formatTimeString(tempDate : Date) : String{
    val curTime : Long = System.currentTimeMillis()
    val regTime : Long = tempDate.time
    var diffTime : Long = (curTime - regTime) / 1000

    var msg : String?

    if(diffTime < TIME_MAXIMUM.SEC){
        msg = "방금 전"
        return msg!!
    }
    if((diffTime / TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN){
        diffTime /= TIME_MAXIMUM.SEC
        msg = "${diffTime}분 전"
        return msg!!
    } else diffTime /= TIME_MAXIMUM.SEC
    if ((diffTime / TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR){
        diffTime /= TIME_MAXIMUM.MIN
        msg = "${diffTime}시간 전"
        return msg!!
    } else diffTime /= TIME_MAXIMUM.MIN
    if((diffTime / TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY){
        diffTime /= TIME_MAXIMUM.HOUR
        msg = "${diffTime}일 전"
        return msg!!
    } else diffTime /= TIME_MAXIMUM.HOUR
    if((diffTime / TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH){
        diffTime /= TIME_MAXIMUM.DAY
        msg = "${diffTime}달 전"
        return msg!!
    }
    else {
        diffTime /= TIME_MAXIMUM.DAY
        msg = "${diffTime}년 전"
        return msg!!
    }
}

