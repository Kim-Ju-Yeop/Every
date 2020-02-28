package com.example.every.viewmodel.student.activity

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

    val content_EditText = MutableLiveData<String>()
    val onImageEvent = SingleLiveEvent<Unit>()

    fun editComment(){
        val bambooReplyEditData = BambooReplyEditData(content_EditText.value.toString())
        val res : Call<Response<Data>> = netRetrofit.bamboo.editBambooReply(StudentData.token.value!!, bambooReplyEditData, idx.value!!)
        res.enqueue(object : Callback<Response<Data>>{
            override fun onResponse(call: Call<Response<Data>>, response: retrofit2.Response<Response<Data>>) {
                if(response.code() == 200) onSuccessEvent.call()
            }
            override fun onFailure(call: Call<Response<Data>>, t: Throwable) {

            }
        })
    }
    fun addImage() = onImageEvent.call()
}