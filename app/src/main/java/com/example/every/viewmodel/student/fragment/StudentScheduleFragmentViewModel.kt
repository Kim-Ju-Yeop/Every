package com.example.every.viewmodel.student.fragment

import android.util.Log
import com.example.every.DTO.student.schedule.SchedulesList
import com.example.every.base.BaseViewModel
import com.example.every.base.StudentData
import com.example.every.network.Data
import com.example.every.network.Response
import com.example.every.widget.SingleLiveEvent
import com.prolificinteractive.materialcalendarview.CalendarDay
import retrofit2.Call
import retrofit2.Callback
import java.text.SimpleDateFormat

class StudentScheduleFragmentViewModel : BaseViewModel(){

    val simpleDataFormat = SimpleDateFormat("yyyy-MM-dd")
    val dates = ArrayList<CalendarDay>()

    var scheduleData = ArrayList<SchedulesList>()
    val scheduleDataList = ArrayList<SchedulesList>()

    val onScheduleSuccessEvent = SingleLiveEvent<Unit>()
    val onScheduleFailEvent = SingleLiveEvent<Unit>()

    fun getSchedule(check: Int, date : String? = null){
        val res : Call<Response<Data>> = netRetrofit.schedule.getSchedule(StudentData.token.value.toString())
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                if(check == 0){
                    if(response.code() == 200){
                        if(!response.body()!!.data!!.schedules.isNullOrEmpty()){
                            for(A in response.body()!!.data!!.schedules!!.indices)
                                dates.add(CalendarDay.from(simpleDataFormat.parse(response.body()!!.data!!.schedules!!.get(A).start_date)))
                            onSuccessEvent.call()
                        }else onFailEvent.call()
                    }
                }else{
                    if(response.code() == 200){
                        if(!response.body()!!.data!!.schedules.isNullOrEmpty()){
                            scheduleDataList.clear()
                            scheduleData = response!!.body()!!.data!!.schedules as ArrayList<SchedulesList>
                            for(A in scheduleData.indices) if(scheduleData.get(A).start_date.equals(date)) scheduleDataList.add(scheduleData.get(A))
                            onScheduleSuccessEvent.call()
                        }else onScheduleFailEvent.call()
                    }
                }
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("geSchedule[Error]", "스케줄 일정 조회 과정에서 서버와 통신이 되지 않습니다.")
            }
        })
    }
}