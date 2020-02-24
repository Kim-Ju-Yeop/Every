package com.example.every.viewmodel.student.bamboo.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.every.base.view.student.tokenData
import com.example.every.base.viewmodel.student.BaseStudentViewModel
import com.example.every.network.Data
import com.example.every.network.NetRetrofit
import com.example.every.network.Response
import com.example.every.network.request.model.student.BambooPostData
import com.example.every.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback

class BambooPostViewModel : BaseStudentViewModel(){

    /**
     * PostBamboo 대나무숲 게시글 작성 API Response
     * status[200] 게시글 작성 성공
     * status[400] 검증 오류
     */

    val content_EditText = MutableLiveData<String>()
    val onImageEvent = SingleLiveEvent<Unit>()

    fun postBamboo(){
        if(!content_EditText.value.isNullOrEmpty()){
            val bambooPostData = BambooPostData(content_EditText.value.toString())
            val res : Call<Response<Data>> = netRetrofit.bamboo.postBamboo(tokenData.token.value.toString(), bambooPostData)
            res.enqueue(object : Callback<Response<Data>>{
                override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                    if(response.code() == 200) onSuccessEvent.call()
                }
                override fun onFailure(call: Call<Response<Data>>, t: Throwable) {
                    Log.e("postBamboo[Error]", "대나무숲 게시글 작성부분에서 서버와 통신이 되지 않습니다.")
                }
            })
        }else onFailEvent.call()
    }
    fun addImage() = onImageEvent.call()
}