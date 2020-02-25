package com.example.every.viewmodel.student.bamboo.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.every.DTO.student.BambooReplyList
import com.example.every.base.view.student.idxData
import com.example.every.base.view.student.tokenData
import com.example.every.base.viewmodel.student.BaseStudentViewModel
import com.example.every.network.Data
import com.example.every.network.NetRetrofit
import com.example.every.network.Response
import com.example.every.network.request.model.student.BambooReplyData
import com.example.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class BambooCommentViewModel : BaseStudentViewModel(){

    /**
     * getBambooComment 댓글 목록 조회 API Response
     * status[200] 댓글 조회 성공
     */

    val replyEditText = MutableLiveData<String>()
    val commentCountEditText = MutableLiveData<String>()

    var bambooCommentServerData = ArrayList<BambooReplyList>()
    var bambooCommentDataList = ArrayList<BambooReplyList>()

    val onReplyEvent = SingleLiveEvent<Unit>()
    val onReplyEmptyEvent = SingleLiveEvent<Unit>()

    fun getBambooComment() {
        val res: Call<Response<Data>> =
            netRetrofit.bamboo.getBambooComment(tokenData.token.value.toString(), idxData.idx.value!!)
        res.enqueue(object : Callback<Response<Data>> {
            override fun onResponse(
                call: Call<Response<Data>>,
                response: retrofit2.Response<Response<Data>>
            ) {
                when (response.code()) {
                    200 -> {
                        if (!response.body()!!.data!!.replies.isNullOrEmpty()) {
                            bambooCommentDataList.clear()
                            bambooCommentServerData =
                                response!!.body()!!.data!!.replies as ArrayList<BambooReplyList>

                            for (A in 0 until bambooCommentServerData.size) {
                                bambooCommentDataList.add(
                                    BambooReplyList(
                                        bambooCommentServerData.get(A).idx,
                                        bambooCommentServerData.get(A).content,
                                        bambooCommentServerData.get(A).created_at,
                                        bambooCommentServerData.get(A).student_idx
                                    )
                                )
                                onSuccessEvent.call()
                            }
                            commentCountEditText.value = "댓글 ${bambooCommentServerData.size}개"
                        } else onFailEvent.call()
                    }
                }
            }

            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("getBambooComment[Error]", "대나무숲 게시물 댓글 검색 과정에서 서버와 통신이 되지 않습니다.")
            }
        })
    }

    /**
     * postBambooReply 댓글 목록 조회 API Response
     * status[200] 댓글 작 성공
     */

    fun postBambooReply(){
        if(!replyEditText.value.isNullOrEmpty()){
            val bambooReplyData = BambooReplyData(replyEditText.value.toString(), idxData.idx.value!!)
            val res : Call<Response<Data>> = netRetrofit.bamboo.postBambooReply(tokenData.token.value.toString(), bambooReplyData)
            res.enqueue(object : Callback<Response<Data>>{
                override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                    if(response.code() == 200) onReplyEvent.call()
                }
                override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                    Log.e("postBambooReply[Error]", "대나무숲 게시물 댓글 작성 과정에서 서버와 통신이 되지 않습니다.")
                }
            })
         }else onReplyEmptyEvent.call()
    }
}