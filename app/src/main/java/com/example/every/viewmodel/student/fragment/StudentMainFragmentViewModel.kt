package com.example.every.viewmodel.student.fragment

import android.util.Log
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

    var mealsData = ArrayList<MealsList>()
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
                    404 -> onFailEvent.call()
                    410 -> onTokenEvent.call()
                }
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("getMeals[Error]", "급식 조회 과정에서 서버와 통신이 되지 않습니다.")
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