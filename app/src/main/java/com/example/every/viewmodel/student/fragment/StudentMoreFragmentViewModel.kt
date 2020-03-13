package com.example.every.viewmodel.student.fragment

import android.util.Log
import android.widget.TextView
import com.example.every.base.BaseViewModel
import com.example.every.base.StudentData
import com.example.every.network.Data
import com.example.every.network.Response
import com.example.every.widget.SingleLiveEvent
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback

class StudentMoreFragmentViewModel : BaseViewModel(){

    /**
     * getStudentInfo 학생 정보 조회 API Response
     * status[200] 멤버 조회 성공
     */

    val onAccountEvent = SingleLiveEvent<Unit>()
    val onUpdateEvent = SingleLiveEvent<Unit>()

    fun getStudentInfo(studentName : TextView, studentEmail : TextView){
        val res : Call<Response<Data>> = netRetrofit.bamboo.getStudentInfo(StudentData.token.value.toString(), StudentData.studentIdx.value!!)
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                if(response.code() == 200){
                    studentName.text = response.body()!!.data!!.member!!.name
                    studentEmail.text = response.body()!!.data!!.member!!.email
                }
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("getStudentInfo[Error]", "학생 정보 조회 과정에서 서버와 통신이 되지 않았습니다.")
            }
        })
    }

    fun onNextEvent() = onAccountEvent.call()
    fun onUpdateEvent() = onUpdateEvent.call()
}