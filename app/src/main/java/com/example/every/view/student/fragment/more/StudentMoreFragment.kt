package com.example.every.view.student.fragment.more

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.every.R
import com.example.every.base.BaseFragment
import com.example.every.databinding.FragmentStudentMoreBinding
import com.example.every.view.student.activity.more.StudentAccountActivity
import com.example.every.view.student.fragment.more.adapter.SliderAdapter
import com.example.every.viewmodel.student.fragment.StudentMoreFragmentViewModel
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

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
        }
    }
}
