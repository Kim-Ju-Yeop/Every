package com.project.every.viewmodel.student.activity.more

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.project.every.base.BaseViewModel
import com.project.every.base.StudentData
import com.project.every.network.Data
import com.project.every.network.Response
import com.project.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class StudentAccountViewModel : BaseViewModel(){

    /**
     * getStudentInfo 학생 정보 조회 API Response
     * status[200] 멤버 조회 성공
     */

    // MutableLiveData
    var schoolId = MutableLiveData<String>()

    // SingleLiveEvent
    val onLogoutEvent = SingleLiveEvent<Unit>()

    fun getStudentInfo(studentName : TextView, studentEmail : TextView, studentPhone : TextView, studentSchoolName : TextView, studentBirth : TextView){
        val res : Call<Response<Data>> = netRetrofit.bamboo.getStudentInfo(StudentData.token.value.toString(), StudentData.studentIdx.value!!)
        res.enqueue(object : Callback<Response<Data>> {
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                if(response.code() == 200){
                    studentName.text = response.body()!!.data!!.member!!.name
                    studentEmail.text = response.body()!!.data!!.member!!.email
                    studentPhone.text = "TEL. ${response.body()!!.data!!.member!!.phone}"
                    studentBirth.text = "${response.body()!!.data!!.member!!.birth_year}년생"

                    schoolId.value = response.body()!!.data!!.member!!.school_id
                    getSchoolInfo(studentSchoolName)
                }
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("getStudentInfo[Error]", "학생 정보 조회 과정에서 서버와 통신이 되지 않았습니다.")
            }
        })
    }

    /**
     * getSchoolInfo 학교 정보 조회 API Response
     * status[200] 학교 정보 조회 성공
     */

    fun getSchoolInfo(studentSchoolName : TextView){
        val res : Call<Response<Data>> = netRetrofit.more.getSchoolInfo(schoolId.value.toString())
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                if(response.code() == 200) studentSchoolName.text = "${response.body()!!.data!!.school!!.school_name} 재학중"
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("getSchoolInfo[Error]", "학교 정보 조회 과정에서 서버와 통신 되지 않았습니다.")
            }
        })
    } fun logout() = onLogoutEvent.call()
}