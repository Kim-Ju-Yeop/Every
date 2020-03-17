package com.project.every.view.student.fragment.schedule

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.project.every.R
import com.project.every.base.BaseFragment
import com.project.every.databinding.FragmentStudentScheduleBinding
import com.project.every.view.student.activity.schedule.SchedulePostActivity
import com.project.every.view.student.fragment.schedule.adapter.NoScheduleAdapter
import com.project.every.view.student.fragment.schedule.adapter.ScheduleAdapter
import com.project.every.view.student.fragment.schedule.decorator.EventDecorator
import com.project.every.viewmodel.student.fragment.StudentScheduleFragmentViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.text.SimpleDateFormat
import java.util.*

class StudentScheduleFragment : BaseFragment() {

    lateinit var binding : FragmentStudentScheduleBinding
    lateinit var viewModel : StudentScheduleFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_schedule, container, false)
        viewModel = ViewModelProviders.of(this@StudentScheduleFragment).get(StudentScheduleFragmentViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@StudentScheduleFragment

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        materialCalendarViewEvent()
        viewModel.getSchedule(0)
    }

    private fun materialCalendarViewEvent(){
        binding.materialCalendarView.selectedDate = CalendarDay.today()
        val date: Date = SimpleDateFormat("yyyy-MM-dd").parse("${CalendarDay.today().year}-${CalendarDay.today().month+1}-${CalendarDay.today().day}")
        val todayDate = SimpleDateFormat("yyyy-MM-dd").format(date)
        viewModel.getSchedule(1, todayDate)

        binding.materialCalendarView.setOnDateChangedListener { widget, date, selected ->
            val date: Date = SimpleDateFormat("yyyy-MM-dd").parse("${date.year}-${date.month+1}-${date.day}")
            val start_date = SimpleDateFormat("yyyy-MM-dd").format(date)
            viewModel.getSchedule(1, start_date)
        }
    }

    override fun observerViewModel() {
        with(viewModel){
            onDecoratorEvent.observe(this@StudentScheduleFragment, Observer {
                binding.materialCalendarView.addDecorator(EventDecorator(binding.root.context, viewModel.dates))
            })
            onScheduleSuccessEvent.observe(this@StudentScheduleFragment, Observer {
                val adapter = ScheduleAdapter(binding.root.context, viewModel.scheduleDataList)
                binding.recyclerView.adapter = adapter
            })
            onScheduleFailureEvent.observe(this@StudentScheduleFragment, Observer {
                val adapter = NoScheduleAdapter(binding.root.context)
                binding.recyclerView.adapter = adapter
            })
            onScheduleNextEvent.observe(this@StudentScheduleFragment, Observer {
                startActivity(Intent(binding.root.context, SchedulePostActivity::class.java))
            })
        }
    }
}
