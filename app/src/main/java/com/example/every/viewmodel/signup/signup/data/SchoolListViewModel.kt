package com.example.every.viewmodel.signup.signup.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.every.DTO.SchoolDataList
import com.example.every.network.Data
import com.example.every.network.NetRetrofit
import com.example.every.network.Response
import com.example.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class SchoolListViewModel : ViewModel(){

    val neRetrofit = NetRetrofit()
    val schoolName = MutableLiveData<String>()

    var schoolServerData = ArrayList<SchoolDataList>()
    var schoolDataList = ArrayList<SchoolDataList>()

    val onSuccessEvent = SingleLiveEvent<Unit>()
    val onNotFoundEvent = SingleLiveEvent<Unit>()
    val onErrorEvent = SingleLiveEvent<Unit>()
    val onServerConnectErrorEvent = SingleLiveEvent<Unit>()

    fun onSearch(){
        val res : Call<Response<Data>> = neRetrofit.signUp.searchSchool(schoolName.value.toString())
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {

                if(response.code() == 200){
                    if(!response.body()?.data?.schools.isNullOrEmpty()){
                        schoolDataList.clear()
                        schoolServerData = response.body()?.data?.schools as ArrayList<SchoolDataList>

                        if(schoolServerData != null){
                            for(A in 0 until schoolServerData.size){
                                schoolDataList.add(SchoolDataList(schoolServerData.get(A).school_id, schoolServerData.get(A).office_id, schoolDataList.get(A).school_name, schoolDataList.get(A).school_location))
                                onSuccessEvent.call()
                            }
                        }
                    }
                    else onNotFoundEvent.call()
                }
                else if(response.code() == 500) onErrorEvent.call()
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                onServerConnectErrorEvent.call()
            }
        })
    }
}