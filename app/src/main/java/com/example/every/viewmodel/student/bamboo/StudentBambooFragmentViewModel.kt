package com.example.every.viewmodel.student.bamboo

import android.util.Log
import com.example.every.DTO.student.BambooPostList
import com.example.every.network.Data
import com.example.every.network.Response
import com.example.every.base.viewmodel.student.BaseStudentViewModel
import com.example.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class StudentBambooFragmentViewModel : BaseStudentViewModel(){

    /**
     * bambooPostList 대나무숲 게시글 조회 API Response
     * status[200] 게시글 조회 성공
     * status[200] 게시글 존재하지 않음
     * status[400] 토큰 존재하지 않음
     * status[401] 위조된 토큰
     * status[410] 토큰 기한 만료
     */

    val onPostEvent = SingleLiveEvent<Unit>()

    var bambooPostServerData = ArrayList<BambooPostList>()
    var bambooPostDataList = ArrayList<BambooPostList>()

    fun getBambooPost(token : String){
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
                                        bambooPostServerData.get(A).content,
                                        bambooPostServerData.get(A).created_at
                                    )
                                )
                                onSuccessEvent.call()
                            }
                        } else onFailEvent.call()
                    }
                    410 -> onTokenEvent.call()
                }
            }
           override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
               Log.e("bambooPostList[Error]", "대나무숲 게시물 검색 과정에서 서버와 통신이 되지 않습니다.")
            }
        })
    }
    fun newPost() = onPostEvent.call()
}