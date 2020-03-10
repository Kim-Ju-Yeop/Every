package com.example.every.viewmodel.student.activity.schedule

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.every.base.BaseViewModel
import com.example.every.base.StudentData
import com.example.every.network.Data
import com.example.every.network.Response
import com.example.every.widget.SingleLiveEvent
import kotlinx.android.synthetic.main.schedule_item.view.*
import retrofit2.Call
import retrofit2.Callback

class ScheduleContentViewModel : BaseViewModel(){

    val idx = MutableLiveData<Int>()

    val onTrashEvent = SingleLiveEvent<Unit>()
    val onEditEvent = SingleLiveEvent<Unit>()

    fun deleteSchedule(){
        val res : Call<Response<Data>> = netRetrofit.schedule.deleteSchedule(StudentData.token.value.toString(), idx.value!!)
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                if(response.code() == 200) onNextEvent.call()
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("deleteSchedule[Error]", "스케줄 삭제 과정에서 서버와 통신되지 않았습니다.")
            }
        })
    }

    fun next() = onNextEvent.call()
    fun trash() = onTrashEvent.call()
    fun edit() = onEditEvent.call()
}