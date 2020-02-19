package com.example.every.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.activity.base.student.BaseStudentFragment
import com.example.every.activity.base.student.tokenData
import com.example.every.databinding.FragmentStudentBambooBinding
import com.example.every.network.retrofit.interfaces.student.Bamboo
import com.example.every.viewmodel.student.fragment.StudentBambooFragmentViewModel

class StudentBambooFragment : BaseStudentFragment() {

    lateinit var binding : FragmentStudentBambooBinding
    lateinit var viewModel : StudentBambooFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_bamboo, container, false)
        viewModel = ViewModelProviders.of(this@StudentBambooFragment).get(StudentBambooFragmentViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@StudentBambooFragment

        viewModel.bambooPostList(tokenData.token.value.toString())
        observerViewModel()
        return binding.root
    }

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@StudentBambooFragment, Observer {
                val adapter = BambooAdapter(context!!.applicationContext, viewModel.bambooPostDataList)
                binding.recyclerView.adapter = adapter
            })
            onFailEvent.observe(this@StudentBambooFragment, Observer {

            })
        }
    }
}
