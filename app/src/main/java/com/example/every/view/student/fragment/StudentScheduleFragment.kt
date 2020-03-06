package com.example.every.view.student.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.base.BaseFragment
import com.example.every.databinding.FragmentStudentScheduleBinding
import com.example.every.viewmodel.student.fragment.StudentScheduleFragmentViewModel

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

    override fun observerViewModel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
