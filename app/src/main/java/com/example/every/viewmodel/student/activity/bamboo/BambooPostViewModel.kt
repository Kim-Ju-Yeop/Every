package com.example.every.viewmodel.student.activity.bamboo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.every.base.BaseViewModel
import com.example.every.base.StudentData
import com.example.every.network.Data
import com.example.every.network.Response
import com.example.every.network.request.model.student.bamboo.BambooPostData
import com.example.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class BambooPostViewModel : BaseViewModel(){

    /**
     * PostBamboo 대나무숲 게시글 작성 API Response
     * status[200] 게시글 작성 성공 : onBambooPostSuccessEvent
     */

    // MutableLiveData
    val content_EditText = MutableLiveData<String>()

    // SingleLiveEvent
    val onBambooPostSuccessEvent = SingleLiveEvent<Unit>()
    val onBambooPostFailureEvent = SingleLiveEvent<Unit>()
    val onBambooPostImageEvent = SingleLiveEvent<Unit>()
    val onBambooIconEvent = SingleLiveEvent<Unit>()

    fun postBamboo(){
        if(!content_EditText.value.isNullOrEmpty()){
            val bambooPostData = BambooPostData(content_EditText.value.toString())
            val res : Call<Response<Data>> = netRetrofit.bamboo.postBamboo(StudentData.token.value.toString(), bambooPostData)
            res.enqueue(object : Callback<Response<Data>>{
                override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                    when(response.code()){
                        200 -> onBambooPostSuccessEvent.call()
                        500 -> onBambooIconEvent.call()
                    }
                }
                override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                    Log.e("postBamboo[Error]", "대나무숲 게시글 작성부분에서 서버와 통신이 되지 않습니다.")
                }
            })
        }else onBambooPostFailureEvent.call()
    }
    fun addImage() = onBambooPostImageEvent.call()
}