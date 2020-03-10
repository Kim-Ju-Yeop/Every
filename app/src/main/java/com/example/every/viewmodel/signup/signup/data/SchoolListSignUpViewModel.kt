package com.example.every.viewmodel.signup.signup.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.every.DTO.signup.SchoolDataList
import com.example.every.base.BaseViewModel
import com.example.every.network.Data
import com.example.every.network.Response
import com.example.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class SchoolListSignUpViewModel : BaseViewModel(){

    /**
     *  getSchool 학교 검색 API Response
     *  status[200] 학교 정보 존재 : onGetSchoolSuccessEvent
     *  status[200] 학교 정보 없음 : onGetSchoolFailureEvent
     *  status[400] 검증 오류
     */

    // MutableLiveData
    val schoolName = MutableLiveData<String>()

    // ArrayList
    var schoolServerData = ArrayList<SchoolDataList>()
    var schoolDataList = ArrayList<SchoolDataList>()

    // SingleLiveEvent
    val onGetSchoolSuccessEvent = SingleLiveEvent<Unit>()
    val onGetSchoolFailureEvent = SingleLiveEvent<Unit>()

    fun getSchool(){
        val res : Call<Response<Data>> = netRetrofit.signUp.getSchool(schoolName.value.toString().trim())
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                when(response.code()){
                    200 -> {
                        if(!response.body()?.data?.schools.isNullOrEmpty()){
                            schoolDataList.clear()
                            schoolServerData = response.body()?.data?.schools as ArrayList<SchoolDataList>

                            for(A in 0 until schoolServerData.size){
                                schoolDataList.add(SchoolDataList(
                                        schoolServerData.get(A).school_id,
                                        schoolServerData.get(A).office_id,
                                        schoolServerData.get(A).school_name,
                                        schoolServerData.get(A).school_location
                                    )
                                )
                                onGetSchoolSuccessEvent.call()
                            }
                        } else onGetSchoolFailureEvent.call()
                    }
                }
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("getSchool[Error]", "학교 검색 과정에서 서버와 통신이 되지 않습니다.")
            }
        })
    }
}