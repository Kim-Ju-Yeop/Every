package com.project.every.view.student.fragment.bamboo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.project.every.R
import com.project.every.view.student.activity.bamboo.BambooPostActivity
import com.project.every.base.BaseFragment
import com.project.every.databinding.FragmentStudentBambooBinding
import com.project.every.view.student.fragment.bamboo.adapter.BambooPostAdapter
import com.project.every.viewmodel.student.fragment.StudentBambooFragmentViewModel

class StudentBambooFragment : BaseFragment() {

    lateinit var binding : FragmentStudentBambooBinding
    lateinit var viewModel : StudentBambooFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_bamboo, container, false)
        viewModel = ViewModelProviders.of(this@StudentBambooFragment).get(StudentBambooFragmentViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@StudentBambooFragment

        refreshLayout()
        viewModel.getBambooPost()
        return binding.root
    }

    private fun refreshLayout(){
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getBambooPost()
            Handler().postDelayed({ binding.swipeRefreshLayout.isRefreshing = false }, 500)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.getBambooPost()
    }

    override fun observerViewModel(){
        with(viewModel){
            onBambooSuccessEvent.observe(this@StudentBambooFragment, Observer {
                val adapter = BambooPostAdapter(context!!.applicationContext, viewModel.bambooPostDataList)
                binding.recyclerView.adapter = adapter
            })
            onBambooFailureEvent.observe(this@StudentBambooFragment, Observer {
                toastMessage(binding.root.context, "현재 게시물이 아무것도 존재하지 않습니다. 가장 먼저 글을 작성해보세요!")
            })
            onBambooNextEvent.observe(this@StudentBambooFragment, Observer {
                val intent = Intent(binding.root.context, BambooPostActivity::class.java)
                startActivityForResult(intent, 1)
            })
        }
    }
}
