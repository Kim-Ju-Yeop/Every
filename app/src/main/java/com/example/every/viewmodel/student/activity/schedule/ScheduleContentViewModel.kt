package com.example.every.viewmodel.student.activity.schedule

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.every.base.BaseViewModel
import com.example.every.base.StudentData
import com.example.every.network.Data
import com.example.every.network.Response
import kotlinx.android.synthetic.main.schedule_item.view.*
import retrofit2.Call
import retrofit2.Callback

class ScheduleContentViewModel : BaseViewModel(){

    val idx = MutableLiveData<Int>()
}