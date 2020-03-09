package com.example.every.viewmodel.student.activity

import android.app.DatePickerDialog
import androidx.lifecycle.MutableLiveData
import com.example.every.base.BaseViewModel
import com.example.every.widget.SingleLiveEvent
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.text.SimpleDateFormat
import java.util.*

class SchedulePostViewModel : BaseViewModel(){

    val title =  MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val start_date = MutableLiveData<String>()
    val end_date = MutableLiveData<String>()
    val check_date = MutableLiveData<Int>()

    val onSelectedEvent = SingleLiveEvent<Unit>()

    fun chooseDate(check : Int){
        check_date.value = check
        onSelectedEvent.call()
    }
}