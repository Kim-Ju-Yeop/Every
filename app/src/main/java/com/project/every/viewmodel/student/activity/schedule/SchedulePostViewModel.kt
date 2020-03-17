package com.project.every.viewmodel.student.activity.schedule

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.every.base.BaseViewModel
import com.project.every.base.StudentData
import com.project.every.network.Data
import com.project.every.network.Response
import com.project.every.network.request.model.student.schedule.SchedulePostData
import com.project.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class SchedulePostViewModel : BaseViewModel(){

    /**
     * postSchedule 스케줄 일정 추가 API Response
     * status[200] 스케줄 일정 추가 성공 : onSchedulePostSuccessEvent
     */

    // MutableLiveData
    val title =  MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val start_date = MutableLiveData<String>()
    val end_date = MutableLiveData<String>()
    val check_date = MutableLiveData<Int>()

    // SingleLiveEvent
    val onSelectedEvent = SingleLiveEvent<Unit>()
    val onSchedulePostSuccessEvent = SingleLiveEvent<Unit>()
    val onSchedulePostFailureEvent = SingleLiveEvent<Unit>()

    fun postSchedule(){
        if(checkData()){
            val schedulePostData = SchedulePostData(title.value.toString(), content.value.toString(), start_date.value.toString(), end_date.value.toString())
            val res : Call<Response<Data>> = netRetrofit.schedule.postSchedule(StudentData.token.value.toString(), schedulePostData)
            res.enqueue(object : Callback<Response<Data>>{
                override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                    if(response.code() == 200) onSchedulePostSuccessEvent.call()
                }
                override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                    Log.e("postSchedule[Error]", "스케줄 일정 추가 작업에서 서버와 통신이 되지 않습니다.")
                }
            })
        }else onSchedulePostFailureEvent.call()
    }

    fun chooseDate(check : Int){
        check_date.value = check
        onSelectedEvent.call()
    }

    fun checkData() : Boolean{
        return if(!title.value.isNullOrBlank()){
            if(!content.value.isNullOrBlank()){
                if(start_date.value!!.substring(0, 4) <= end_date.value!!.substring(0, 4) &&
                    start_date.value!!.substring(5, 7) <= end_date.value!!.substring(5, 7) &&
                    start_date.value!!.substring(8, 10) <= end_date.value!!.substring(8, 10)){
                    return true
                }else false
            }else false
        }else false
    }
}