package com.example.every.viewmodel.student.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.every.base.BaseViewModel
import com.example.every.base.StudentData
import com.example.every.network.Data
import com.example.every.network.Response
import com.example.every.network.request.model.student.BambooReplyEditData
import com.example.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class BambooCommentEditViewModel : BaseViewModel(){

    val idx = MutableLiveData<Int>()

    val comment_EditText = MutableLiveData<String>()

    val onImageEvent = SingleLiveEvent<Unit>()
    val onReplyEmptyEvent = SingleLiveEvent<Unit>()

    /**
     * editComment 대나무숲 게시글 댓글 수정 API Response
     * status[200] 댓글 수정 성공
     */

    fun editComment(){
        if(!comment_EditText.value.isNullOrEmpty()){
            val bambooReplyEditData = BambooReplyEditData(comment_EditText.value.toString())
            val res : Call<Response<Data>> = netRetrofit.bamboo.editBambooReply(StudentData.token.value!!, bambooReplyEditData, idx.value!!)
            res.enqueue(object : Callback<Response<Data>>{
                override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                    if(response.code() == 200) onSuccessEvent.call()
                }
                override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                    Log.e("editComment[Error]", "대나무숲 게시글 댓글 수정 과정에서 서버와 통신이 되지 않았습니다.")
                }
            })
        } else onReplyEmptyEvent.call()
    } fun addImage() = onImageEvent.call()
}