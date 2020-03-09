package com.example.every.view.student.fragment.schedule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.base.BaseFragment
import com.example.every.databinding.FragmentStudentScheduleBinding
import com.example.every.viewmodel.student.fragment.StudentScheduleFragmentViewModel
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

        materialCalendarViewEvent()
        viewModel.getSchedule(0)
        return binding.root
    }

    private fun materialCalendarViewEvent(){
        binding.materialCalendarView.setSelectedDate(CalendarDay.today())
        val date: Date = SimpleDateFormat("yyyy-MM-dd").parse("${CalendarDay.today().year}-${CalendarDay.today().month+1}-${CalendarDay.today().day}")
        val start_date = SimpleDateFormat("yyyy-MM-dd").format(date)
        viewModel.getSchedule(1, start_date)

        binding.materialCalendarView.setOnDateChangedListener { widget, date, selected ->
            val date: Date = SimpleDateFormat("yyyy-MM-dd").parse("${date.year}-${date.month+1}-${date.day}")
            val start_date = SimpleDateFormat("yyyy-MM-dd").format(date)
            viewModel.getSchedule(1, start_date)
        }
    }

    override fun observerViewModel() {
        with(viewModel){
            onSuccessEvent.observe(this@StudentScheduleFragment, Observer {
                binding.materialCalendarView.addDecorator(EventDecorator(binding.root.context, viewModel.dates))
            })
            onFailEvent.observe(this@StudentScheduleFragment, Observer {
                // 일정이 아무것도 존재하지 않는 상황
            })
            onScheduleSuccessEvent.observe(this@StudentScheduleFragment, Observer {
                val adapter = ScheduleAdapter(binding.root.context, viewModel.scheduleDataList)
                binding.recyclerView.adapter = adapter
            })
            onScheduleFailEvent.observe(this@StudentScheduleFragment, Observer {
                // 스케줄이 아무것도 존재하지 않는 경우
            })
        }
    }
}
