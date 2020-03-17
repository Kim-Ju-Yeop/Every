package com.project.every.view.student.fragment.more

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
import com.project.every.databinding.FragmentStudentMoreBinding
import com.project.every.view.student.activity.more.StudentAccountActivity
import com.project.every.view.student.activity.more.StudentUpdateActivity
import com.project.every.view.student.fragment.more.adapter.SliderAdapter
import com.project.every.viewmodel.student.fragment.StudentMoreFragmentViewModel

class StudentMoreFragment : BaseFragment() {

    lateinit var binding : FragmentStudentMoreBinding
    lateinit var viewModel : StudentMoreFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_more, container, false)
        viewModel = ViewModelProviders.of(this@StudentMoreFragment).get(StudentMoreFragmentViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@StudentMoreFragment

        sliderViewSetting()
        viewModel.getStudentInfo(binding.studentNameTextView, binding.studentEmailTextView)
        return binding.root
    }

    private fun sliderViewSetting(){
        val adapter = SliderAdapter(binding.root.context, 5)
        binding.sliderView.sliderAdapter = adapter
    }

    override fun observerViewModel() {
        with(viewModel){
            onAccountEvent.observe(this@StudentMoreFragment, Observer {
                startActivity(Intent(binding.root.context, StudentAccountActivity::class.java))
            })
            onUpdateEvent.observe(this@StudentMoreFragment, Observer {
                startActivity(Intent(binding.root.context, StudentUpdateActivity::class.java))
            })
        }
    }
}
