package com.example.every.view.student.fragment.more

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import com.example.every.R
import com.example.every.databinding.FragmentStudentMoreBinding
import com.example.every.view.student.fragment.more.adapter.SliderAdapter
import com.example.every.viewmodel.student.fragment.StudentMoreFragmentViewModel
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

class StudentMoreFragment : Fragment() {

    lateinit var binding : FragmentStudentMoreBinding
    lateinit var viewModel : StudentMoreFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_more, container, false)
        viewModel = ViewModelProviders.of(this@StudentMoreFragment).get(StudentMoreFragmentViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@StudentMoreFragment

        sliderSetting()
        return binding.root
    }

    private fun sliderSetting(){
        val adapter = SliderAdapter(binding.root.context, 5)
        binding.sliderView.sliderAdapter = adapter
    }
}
