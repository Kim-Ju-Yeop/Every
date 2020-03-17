package com.project.every.viewmodel.student.activity.bamboo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.every.base.BaseViewModel
import com.project.every.base.StudentData
import com.project.every.network.Data
import com.project.every.network.Response
import com.project.every.network.request.model.student.bamboo.BambooReplyEditData
import com.project.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class BambooCommentEditViewModel : BaseViewModel(){

    /**
     * editComment 대나무숲 게시글 댓글 수정 API Response
     * status[200] 댓글 수정 성공 : onBambooCommentReplySuccessEvent
     */

    // MutableLiveData
    val idx = MutableLiveData<Int>()
    val comment_EditText = MutableLiveData<String>()

    // SingleLiveEvent
    val onBambooCommentReplySuccessEvent = SingleLiveEvent<Unit>()
    val onBambooCommentReplyEmptyEvent = SingleLiveEvent<Unit>()
    val onBambooCommentEditImageEvent = SingleLiveEvent<Unit>()

    fun editComment(){
        if(!comment_EditText.value.isNullOrEmpty()){
            val bambooReplyEditData = BambooReplyEditData(comment_EditText.value.toString())
            val res : Call<Response<Data>> = netRetrofit.bamboo.editBambooReply(StudentData.token.value!!, bambooReplyEditData, idx.value!!)
            res.enqueue(object : Callback<Response<Data>>{
                override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                    if(response.code() == 200) onBambooCommentReplySuccessEvent.call()
                }
                override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                    Log.e("editComment[Error]", "대나무숲 게시글 댓글 수정 과정에서 서버와 통신이 되지 않았습니다.")
                }
            })
        } else onBambooCommentReplyEmptyEvent.call()
    } fun addImage() = onBambooCommentEditImageEvent.call()
}