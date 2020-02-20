package com.example.every.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import com.example.every.R
import com.example.every.activity.base.student.BaseStudentFragment
import com.example.every.activity.base.student.tokenData
import com.example.every.databinding.FragmentStudentMainBinding
import com.example.every.viewmodel.student.StudentMainFragmentViewModel

class StudentMainFragment : BaseStudentFragment() {

    lateinit var binding : FragmentStudentMainBinding
    lateinit var viewModel : StudentMainFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_main, container, false)
        viewModel = ViewModelProviders.of(this@StudentMainFragment).get(StudentMainFragmentViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@StudentMainFragment

        val checkToken : SharedPreferences = context!!.getSharedPreferences("checkToken", Context.MODE_PRIVATE)
        tokenData.token.value = checkToken.getString("tokenData", "hello")
        return binding.root
    }
}
