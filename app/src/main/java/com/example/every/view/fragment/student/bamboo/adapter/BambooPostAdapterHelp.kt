package com.example.every.view.fragment.student.bamboo.adapter

import android.util.Log
import android.widget.TextView
import com.example.every.network.Data
import com.example.every.network.NetRetrofit
import com.example.every.network.Response
import retrofit2.Call
import retrofit2.Callback
import java.util.*

object TIME_MAXIMUM{
    var SEC = 60
    var MIN = 60
    var HOUR = 24
    var DAY = 30
    var MONTH = 12
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

fun getWeek(dayNum : Int) : String{
    var day : String? = null

    when (dayNum) {
        1 -> day = "일요일"
        2 -> day = "월요일"
        3 -> day = "화요일"
        4 -> day = "수요일"
        5 -> day = "목요일"
        6 -> day = "금요일"
        7 -> day = "토요일"
    }
    return day.toString()
}

fun getBambooComment(token : String, idx : Int, comment : TextView){

    /**
     * getBambooComment 댓글 조회 API Response
     * status[200] 댓글 조회 성공
     * status[200] 댓글 존재하지 않
     */

    val netRetrofit = NetRetrofit()

    val res : Call<Response<Data>> = netRetrofit.bamboo.getBambooComment(token, idx)
    res.enqueue(object : Callback<Response<Data>>{
        override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
            if(response.code() == 200) comment.text = "댓글 ${response.body()!!.data!!.replies!!.size}개"
        }
        override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
            Log.e("replyNumber[Error]", "대나무숲 게시물의 댓글 갯수를 찾는 과정에서 서버와 통신이 되지 않습니다.")
        }
    })
}