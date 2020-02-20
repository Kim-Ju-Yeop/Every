package com.example.every.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.activity.base.student.BaseStudentFragment
import com.example.every.activity.base.student.tokenData
import com.example.every.activity.signin.SignInActivity
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
                // 게시글 데이터 존재하지 않음
            })
            onTokenEvent.observe(this@StudentBambooFragment, Observer {

                // SignInData Setting
                val loginData = context!!.applicationContext.getSharedPreferences("checkLogin", Context.MODE_PRIVATE)
                val loginData_editor = loginData.edit()

                loginData_editor.putBoolean("loginData", false)
                loginData_editor.commit()

                Toast.makeText(context!!.applicationContext, "토큰이 만료되었습니다. 로그인 화면으로 이동합니다.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(context!!.applicationContext, SignInActivity::class.java))
                activity!!.finish()
            })
        }
    }
}
