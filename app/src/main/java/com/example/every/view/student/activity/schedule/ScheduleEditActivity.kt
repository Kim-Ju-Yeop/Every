package com.example.every.view.student.activity.schedule

import android.app.DatePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.base.BaseActivity
import com.example.every.base.BaseFragment
import com.example.every.databinding.ActivityScheduleEditBinding
import com.example.every.viewmodel.student.activity.schedule.ScheduleEditViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.android.synthetic.main.bamboo_item.*
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
        }
    }
}
