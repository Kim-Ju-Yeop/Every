package com.project.every.viewmodel.student.activity.schedule

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.every.base.BaseViewModel
import com.project.every.base.StudentData
import com.project.every.network.Data
import com.project.every.network.Response
import com.project.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class ScheduleContentViewModel : BaseViewModel(){

    /**
     * getIdxSchedule 게시글 일부 조회 API Response
     * status[200] 게시글 일부 조회 성공
     */

    // MutableLiveData
    val idx = MutableLiveData<Int>()
    val title = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val date = MutableLiveData<String>()

    fun getIdxSchedule(){
        val res : Call<Response<Data>> = netRetrofit.schedule.getIdxSchedule(StudentData.token.value.toString(), idx.value!!)
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                if(response.code() == 200){
                    val scheduleData = response.body()!!.data!!.schedule

                    title.value = scheduleData!!.title
                    content.value = scheduleData!!.content
                    date.value = "${scheduleData!!.start_date.substring(0, 4)}년 ${scheduleData!!.start_date.substring(5, 7)}월 " +
                                 "${scheduleData!!.start_date.substring(8, 10)}일 ~ ${scheduleData!!.end_date.substring(0, 4)}년 " +
                                 "${scheduleData!!.end_date.substring(5, 7)}월 ${scheduleData!!.end_date.subSequence(8, 10)}일"
                }
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("getIdxSchedule[Error]", "스케줄 일부 조회 과정에서 서버와 통신되지 않습니다.")
            }
        })
    }


    /**
     * deleteSchedule 게시글 삭제 API Response
     * status[200] 게시글 삭제 성공
     */

    // SingleLiveEvent
    val onTrashEvent = SingleLiveEvent<Unit>()
    val onEditEvent = SingleLiveEvent<Unit>()
    val onBackEvent = SingleLiveEvent<Unit>()

    fun deleteSchedule(){
        val res : Call<Response<Data>> = netRetrofit.schedule.deleteSchedule(StudentData.token.value.toString(), idx.value!!)
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                if(response.code() == 200) onBackEvent.call()
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("deleteSchedule[Error]", "스케줄 삭제 과정에서 서버와 통신되지 않았습니다.")
            }
        })
    }

    fun backSpace() = onBackEvent.call()
    fun trash() = onTrashEvent.call()
    fun edit() = onEditEvent.call()
}