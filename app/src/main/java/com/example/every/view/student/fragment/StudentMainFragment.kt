package com.example.every.view.student.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.base.StudentData
import com.example.every.databinding.FragmentStudentMainBinding
import com.example.every.viewmodel.student.fragment.StudentMainFragmentViewModel

class StudentMainFragment : Fragment() {

    lateinit var binding : FragmentStudentMainBinding
    lateinit var viewModel : StudentMainFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_main, container, false)
        viewModel = ViewModelProviders.of(this@StudentMainFragment).get(StudentMainFragmentViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@StudentMainFragment

        init()
        return binding.root
    }

    fun init(){
        val checkToken : SharedPreferences = context!!.getSharedPreferences("checkToken", Context.MODE_PRIVATE)
        StudentData.token.value = checkToken.getString("tokenData", null)
    }
}
