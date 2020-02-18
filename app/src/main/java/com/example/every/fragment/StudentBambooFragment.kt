package com.example.every.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.activity.base.student.BaseStudentFragment
import com.example.every.databinding.FragmentStudentBambooBinding
import com.example.every.viewmodel.student.fragment.StudentBambooViewModel

class StudentBambooFragment : BaseStudentFragment() {

    lateinit var binding : FragmentStudentBambooBinding
    lateinit var viewModel : StudentBambooViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_bamboo, container, false)
        viewModel = ViewModelProviders.of(this@StudentBambooFragment).get(StudentBambooViewModel::class.java)

        return binding.root
    }
}
