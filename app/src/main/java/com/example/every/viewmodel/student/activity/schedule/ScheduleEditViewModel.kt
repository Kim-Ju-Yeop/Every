package com.example.every.viewmodel.student.activity.schedule

import androidx.lifecycle.MutableLiveData
import com.example.every.base.BaseViewModel
import com.example.every.base.StudentData
import com.example.every.network.Data
import com.example.every.network.Response
import com.example.every.network.request.model.student.schedule.SchedulePostData
import com.example.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class ScheduleEditViewModel : BaseViewModel(){
    val idx = MutableLiveData<Int>()
    val title =  MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val start_date = MutableLiveData<String>()
    val end_date = MutableLiveData<String>()
    val check_date = MutableLiveData<Int>()

    val onSelectedEvent = SingleLiveEvent<Unit>()

    fun chooseDate(check : Int){
        check_date.value = check
        onSelectedEvent.call()
    }

    fun editSchedule(){
        if(checkData()){
            val schedulePostData = SchedulePostData(title.value.toString(), content.value.toString(), start_date.value.toString(), end_date.value.toString())
            val res : Call<Response<Data>> = netRetrofit.schedule.editSchedule(StudentData.token.value.toString(), schedulePostData, idx.value!!)
            res.enqueue(object : Callback<Response<Data>>{
                override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                    if(response.code() == 200) onSuccessEvent.call()
                }
                override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                    // 서버 통신 안됨
                }
            })
        }else onErrorEvent.call()
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