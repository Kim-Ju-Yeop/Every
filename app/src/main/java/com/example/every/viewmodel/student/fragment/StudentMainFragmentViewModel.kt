package com.example.every.viewmodel.student.fragment

import com.example.every.DTO.student.home.MealsList
import com.example.every.base.BaseViewModel
import com.example.every.base.StudentData
import com.example.every.network.Data
import com.example.every.network.Response
import com.example.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class StudentMainFragmentViewModel : BaseViewModel(){

    /**
     * GetMeals 급식 조회 API Response
     * status[200] 급식 조회 성공
     * status[404] 급식 정보 없음
     * status[410] 토큰 만료
     */

    var checkCount = 0

    val mealsData = ArrayList<String>()
    var breakfastList = ArrayList<String>()
    var lunchList = ArrayList<String>()
    var dinnerList = ArrayList<String>()

    val onTokenEvent = SingleLiveEvent<Unit>()
    val onBreakfastEvent = SingleLiveEvent<Unit>()
    val onLunchEvent = SingleLiveEvent<Unit>()
    val onDinnerEvent = SingleLiveEvent<Unit>()

    fun getMeals(){
        val res : Call<Response<Data>> = netRetrofit.home.getMeal(StudentData.token.value.toString())
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                when(response.code()){
                    200 -> {
                        for(A in 0..response.body()!!.data!!.meals!!.size){
                            if(response.body()!!.data!!.meals == null) mealsData.add("null")
                            else mealsData.add(response.body()!!.data!!.meals!!.get(A).meals_name)
                        }
                        for(A in 0..mealsData.size){
                            if(mealsData.get(A) == null) continue
                            else{
                                when(A){
                                    0 -> breakfastList = mealsData.get(A).split("</br>") as ArrayList<String>
                                    1 -> lunchList = mealsData.get(A).split("</br>") as ArrayList<String>
                                    2 -> dinnerList = mealsData.get(A).split("</br>") as ArrayList<String>
                                }
                            }
                        }
                        onBreakfastEvent.call()
                    }
                    404 -> onFailEvent.call()
                    410 -> onTokenEvent.call()
                }
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                println()
            }
        })
    }

    // Next & Back
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
}