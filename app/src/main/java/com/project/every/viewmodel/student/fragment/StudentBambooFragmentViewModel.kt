package com.project.every.viewmodel.student.fragment

import android.util.Log
import com.project.every.DTO.student.bamboo.BambooPostList
import com.project.every.base.BaseViewModel
import com.project.every.base.StudentData
import com.project.every.network.Data
import com.project.every.network.Response
import com.project.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class StudentBambooFragmentViewModel : BaseViewModel(){

    /**
     * bambooPostList 대나무숲 게시글 조회 API Response
     * status[200] 게시글 조회 성공 : onBambooSuccessEvent
     * status[200] 게시글 존재하지 않음 : onBambooFailureEvent
     */

    // ArrayList
    var bambooPostServerData = ArrayList<BambooPostList>()
    var bambooPostDataList = ArrayList<BambooPostList>()

    // SingleLiveEvent
    val onBambooSuccessEvent = SingleLiveEvent<Unit>()
    val onBambooFailureEvent = SingleLiveEvent<Unit>()
    val onBambooNextEvent = SingleLiveEvent<Unit>()

    fun getBambooPost(){
        val res : Call<Response<Data>> = netRetrofit.bamboo.getBambooPost(StudentData.token.value.toString())
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
                                        bambooPostServerData.get(A).content,
                                        bambooPostServerData.get(A).created_at
                                    )
                                )
                                onBambooSuccessEvent.call()
                            }
                        } else onBambooFailureEvent.call()
                    }
                }
            }
           override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
               Log.e("bambooPostList[Error]", "대나무숲 게시물 검색 과정에서 서버와 통신이 되지 않습니다.")
            }
        })
    } fun newPost() = onBambooNextEvent.call()
}