package com.project.every.viewmodel.student.activity.bamboo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.every.base.BaseViewModel
import com.project.every.base.StudentData
import com.project.every.network.Data
import com.project.every.network.Response
import com.project.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class BambooMoreViewModel : BaseViewModel(){

    /**
     * deleteReply 대나무숲 게시글 댓글 삭제 API Response
     * status[200] 댓글 삭제 성공
     */

    // MutableLiveData
    val idx = MutableLiveData<Int>()
    val content = MutableLiveData<String>()

    // SingleLiveEvent
    val onBambooMoreSuccessEvent = SingleLiveEvent<Unit>()
    val onBambooMoreNextEvent = SingleLiveEvent<Unit>()

    fun deleteReply(){
        val res : Call<Response<Data>> = netRetrofit.bamboo.deleteBambooReply(StudentData.token.value.toString(), idx.value!!)
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                if(response.code() == 200) onBambooMoreSuccessEvent.call()
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                Log.e("deleteReply[Error]", "대나무숲 게시물 댓글 삭제 과정에서 서버와 통신이 되지 않습니다.")
           }
        })
    } fun editContent() = onBambooMoreNextEvent.call()
}