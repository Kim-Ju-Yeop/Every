package com.example.every.viewmodel.student.fragment

import android.annotation.SuppressLint
import android.util.Log
import android.widget.TextView
import com.example.every.DTO.student.bamboo.BambooPostList
import com.example.every.DTO.student.home.MealsList
import com.example.every.base.BaseViewModel
import com.example.every.base.StudentData
import com.example.every.network.Data
import com.example.every.network.Response
import com.example.every.widget.SingleLiveEvent
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback

class StudentHomeFragmentViewModel : BaseViewModel(){

    /**
     * getMeals 급식 조회 API Response
     * status[200] 급식 조회 성공 : onBreakfastEvent / onLunchEvent / onDinnerEvent
     * status[404] 급식 정보 없음 : onMealsFailureEvent
     * status[410] 토큰 만료 : onTokenEvent
     */

    // Member
    var checkCount = 1

    // ArrayList
    var mealsData = ArrayList<MealsList>()
    var breakfastList = ArrayList<String>()
    var lunchList = ArrayList<String>()
    var dinnerList = ArrayList<String>()

    // SingleLiveEvent
    val onBreakfastEvent = SingleLiveEvent<Unit>()
    val onLunchEvent = SingleLiveEvent<Unit>()
    val onDinnerEvent = SingleLiveEvent<Unit>()
    val onMealsFailureEvent = SingleLiveEvent<Unit>()
    val onTokenEvent = SingleLiveEvent<Unit>()

    fun getMeals(){
        val res : Call<Response<Data>> = netRetrofit.home.getMeal(StudentData.token.value.toString())
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                when(response.code()){
                    200 -> {
                        mealsData = response.body()!!.data!!.meals as ArrayList<MealsList>

                        for(A in 0 until mealsData.size){
                            when(mealsData.get(A).meal_code){
                                1 -> breakfastList = mealsData.get(A).meal_name.split("<br/>") as ArrayList<String>
                                2 -> lunchList = mealsData.get(A).meal_name.split("<br/>") as ArrayList<String>
                                3 -> dinnerList = mealsData.get(A).meal_name.split("<br/>") as ArrayList<String>
                            }
                        }
                        onLunchEvent.call()
                    }
                    404 -> onMealsFailureEvent.call()
                    410 -> onTokenEvent.call()
                }
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("getMeals[Error]", "급식 조회 과정에서 서버와 통신이 되지 않습니다.")
            }
        })
    }

    fun nextMeals(){
        if(checkCount != 3){
            checkCount++
            when(checkCount){
                1 -> onBreakfastEvent.call()
                2 -> onLunchEvent.call()
                3 -> onDinnerEvent.call()
            }
        }
    }
    fun backMeals(){
        if(checkCount != 1){
            checkCount--
            when(checkCount){
                1 -> onBreakfastEvent.call()
                2 -> onLunchEvent.call()
                3 -> onDinnerEvent.call()
            }
        }
    }

    /**
     * getStudentInfo 멤버 조회 API Response
     * status[200] 멤버 조회 성공
     */

    fun getStudentInfo(student_name : TextView){
        val res : Call<Response<Data>> = netRetrofit.bamboo.getStudentInfo(StudentData.token.value.toString(), StudentData.studentIdx.value!!)
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                if(response.code() == 200) student_name.text = response.body()!!.data!!.member!!.name
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("getStudentInfo[Error]", "멤버 조회 과정에서 서버와 통신이 되지 않습니다.")
            }
        })
    }

    /**
     * getBambooPostOrder 인기 대나무숲 게시글 조회 API Response
     * status[200] 게시글 조회 성공
     */

    // ArrayList
    var bambooOrderList = ArrayList<BambooPostList>()

    // SingleLiveEvent
    val onBambooDataEvent = SingleLiveEvent<Unit>()
    val onLoadingEvent = SingleLiveEvent<Unit>()

    fun getBambooPostOrder(){
        onLoadingEvent.call()
        val res : Call<Response<Data>> = netRetrofit.bamboo.getBambooPostOrder(StudentData.token.value.toString(), "hit")
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                if(response.code() == 200) {
                    bambooOrderList = response.body()!!.data!!.posts as ArrayList<BambooPostList>
                    onBambooDataEvent.call()
                }
            }
            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("getBambooPostOrder[Error]", "대나무숲 인기 게시글 조회 과정에서 서버와 통신이 되지 않습니다.")
            }
        })
    }

    /**
     * getSchedule 스케줄 조회 API Response
     * status[200] 일정 조회 성공 (데이터 존재)
     * status[200] 일정 조회 성공 (데이터 없음)
     */

    // Member
    var counter : Int = 0

    // SingleLiveEvent
    val onScheduleDataEvent = SingleLiveEvent<Unit>()

    fun getSchedule(date : String){
        val res : Call<Response<Data>> = netRetrofit.schedule.getSchedule(StudentData.token.value.toString())
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                if(response.code() == 200){
                    for(A in response.body()!!.data!!.schedules!!.indices)
                        if(response.body()!!.data!!.schedules!!.get(A)!!.start_date.equals(date)) counter++
                    onScheduleDataEvent.call()
                }
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("getSchedule[Error]", "스케줄 일정 조회 과정에서 서버와 통신 되지 않습니다.")
            }
        })
    }

    // SingleLiveEvent
    val onHomeActivityEvent = SingleLiveEvent<Unit>()
    fun activity() = onHomeActivityEvent.call()
}