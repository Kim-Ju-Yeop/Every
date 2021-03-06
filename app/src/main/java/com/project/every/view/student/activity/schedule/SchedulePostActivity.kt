package com.project.every.view.student.activity.schedule

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.project.every.R
import com.project.every.base.BaseActivity
import com.project.every.databinding.ActivitySchedulePostBinding
import com.project.every.viewmodel.student.activity.schedule.SchedulePostViewModel
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
                        binding.postButton.setBackgroundColor(Color.parseColor("#2D008A"))
                        binding.postButton.isEnabled = true
                    } else binding.postButton.setBackgroundColor(Color.GRAY)
                }

                val today : CalendarDay = CalendarDay.today()
                val dialog = DatePickerDialog(binding.root.context, callbackMethod, today.year, today.month, today.day)
                dialog.show()
            })
            onSchedulePostSuccessEvent.observe(this@SchedulePostActivity, androidx.lifecycle.Observer {
                toastMessage(applicationContext, "일정을 성공적으로 추가하였습니다.")
                finish()
            })
            onSchedulePostFailureEvent.observe(this@SchedulePostActivity, androidx.lifecycle.Observer {
                toastMessage(applicationContext, "입력하신 내용을 다시 한 번 확인하십시오.")
            })
            title.observe(this@SchedulePostActivity, androidx.lifecycle.Observer {
                if(title.value!!.length > 15) toastMessage(applicationContext, "15글자 내외로 작성할 수 있습니다.")
            })
            content.observe(this@SchedulePostActivity, androidx.lifecycle.Observer {
                if(content.value!!.length > 150) toastMessage(applicationContext, "150글자 내오로 작성할 수 있습니다.")
            })
        }
    }

    override fun onBackPressed() = finish()
}
