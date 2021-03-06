package com.project.every.view.student.activity.schedule

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.project.every.R
import com.project.every.base.BaseActivity
import com.project.every.databinding.ActivityScheduleEditBinding
import com.project.every.viewmodel.student.activity.schedule.ScheduleEditViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.text.SimpleDateFormat
import java.util.*

class ScheduleEditActivity : BaseActivity() {

    lateinit var binding : ActivityScheduleEditBinding
    lateinit var viewModel : ScheduleEditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@ScheduleEditActivity, R.layout.activity_schedule_edit)
        viewModel = ViewModelProviders.of(this@ScheduleEditActivity).get(ScheduleEditViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@ScheduleEditActivity

        init()
        toolbarInit(binding.toolbar)
    }

    private fun init(){
        val intent = intent
        viewModel.idx.value = intent.extras!!.getInt("idx")
        viewModel.getIdxSchedule()
    }

    override fun observerViewModel() {
        with(viewModel){
            onSelectedEvent.observe(this@ScheduleEditActivity, androidx.lifecycle.Observer {
                val callbackMethod = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val date: Date = SimpleDateFormat("yyyy-MM-dd").parse("$year-${month+1}-$dayOfMonth")
                    val formatDate = SimpleDateFormat("yyyy-MM-dd").format(date)

                    if(viewModel.check_date.value == 0) viewModel.start_date.value = formatDate
                    else viewModel.end_date.value = formatDate
                }

                val today : CalendarDay = CalendarDay.today()
                val dialog = DatePickerDialog(binding.root.context, callbackMethod, today.year, today.month, today.day)
                dialog.show()
            })
            onScheduleEditSuccessEvent.observe(this@ScheduleEditActivity, androidx.lifecycle.Observer {
                toastMessage(applicationContext, "일정을 수정하였습니다.")
                finish()
            })
            onScheduleEditFailureEvent.observe(this@ScheduleEditActivity, androidx.lifecycle.Observer {
                toastMessage(applicationContext, "입력하신 내용을 다시 한 번 확인하십시오.")
            })
            title.observe(this@ScheduleEditActivity, androidx.lifecycle.Observer {
                if(title.value!!.length > 15) toastMessage(applicationContext, "15글자 내외로 작성할 수 있습니다.")
            })
            content.observe(this@ScheduleEditActivity, androidx.lifecycle.Observer {
                if(content.value!!.length > 150) toastMessage(applicationContext, "150글자 내오로 작성할 수 있습니다.")
            })
        }
    }
}
