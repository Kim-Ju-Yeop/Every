package com.example.every.view.student.activity.schedule

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.base.BaseActivity
import com.example.every.databinding.ActivitySchedulePostBinding
import com.example.every.viewmodel.student.activity.schedule.SchedulePostViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.text.SimpleDateFormat
import java.util.*

class SchedulePostActivity : BaseActivity() {

    lateinit var binding : ActivitySchedulePostBinding
    lateinit var viewModel : SchedulePostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@SchedulePostActivity, R.layout.activity_schedule_post)
        viewModel = ViewModelProviders.of(this@SchedulePostActivity).get(SchedulePostViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SchedulePostActivity

        init()
        toolbarInit(binding.toolbar)
    }

    private fun init(){
        viewModel.end_date.value = "날짜 선택"
        viewModel.start_date.value = "날짜 선택"
    }

    override fun observerViewModel() {
        with(viewModel){
            onSelectedEvent.observe(this@SchedulePostActivity, androidx.lifecycle.Observer {
                val callbackMethod = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val date: Date = SimpleDateFormat("yyyy-MM-dd").parse("$year-${month+1}-$dayOfMonth")
                    val formatDate = SimpleDateFormat("yyyy-MM-dd").format(date)

                    if(viewModel.check_date.value == 0) viewModel.start_date.value = formatDate
                    else viewModel.end_date.value = formatDate

                    if(!viewModel.start_date.value.equals("날짜 선택") && !viewModel.end_date.value.equals("날짜 선택")) {
                        binding.postButton.setBackgroundResource(R.drawable.background_schedule_add_button)
                        binding.postButton.isEnabled = true
                    } else {
                        binding.postButton.setBackgroundColor(Color.GRAY)
                        binding.postButton.isEnabled
                    }
                }

                val today : CalendarDay = CalendarDay.today()
                val dialog = DatePickerDialog(binding.root.context, callbackMethod, today.year, today.month, today.day)
                dialog.show()
            })
            onSuccessEvent.observe(this@SchedulePostActivity, androidx.lifecycle.Observer {
                toastMessage(applicationContext, "일정을 성공적으로 추가하였습니다.")
                finish()
            })
            onErrorEvent.observe(this@SchedulePostActivity, androidx.lifecycle.Observer {
                toastMessage(applicationContext, "입력하신 내용을 다시 한 번 확인하십시오.")
            })
        }
    }
}
