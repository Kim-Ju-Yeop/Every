package com.example.every.viewmodel.student.fragment

import android.util.Log
import com.example.every.DTO.student.BambooPostList
import com.example.every.network.Data
import com.example.every.network.Response
import com.example.every.viewmodel.base.student.BaseStudentFragmentViewModel
import retrofit2.Call
import retrofit2.Callback

class StudentBambooFragmentViewModel : BaseStudentFragmentViewModel(){

    var bambooPostServerData = ArrayList<BambooPostList>()
    var bambooPostDataList = ArrayList<BambooPostList>()

    fun bambooPostList(token : String){
        val res : Call<Response<Data>> = netRetrofit.bamboo.getBambooPost(token)
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                when(response.code()){
                    200 -> {
                        if(!response.body()!!.data!!.posts.isNullOrEmpty()){
                            bambooPostDataList.clear()
                            bambooPostServerData = response!!.body()!!.data!!.posts as ArrayList<BambooPostList>

                            for(A in 0 until bambooPostServerData.size){
                                bambooPostDataList.add(
                                    BambooPostList(
                                        bambooPostServerData.get(A).idx,
                                        bambooPostServerData.get(A).title,
                                        bambooPostServerData.get(A).content,
                                        bambooPostServerData.get(A).created_at
                                    )
                                )
                                onSuccessEvent.call()
                            }
                        } else{
                            onFailEvent.call()
                        }
                    }
                }
            }
           override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
               Log.e("bambooPostList[Error]", "대나무숲 게시물 검색 과정에서 서버와 통신이 되지 않습니다.")
            }
        })
    }
}