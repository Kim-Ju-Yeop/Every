package com.project.every.view.signup

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.project.every.R
import com.project.every.view.signup.signup.data.Email_PwSignUpActivity
import com.project.every.base.BaseActivity
import com.project.every.base.SignUpData
import com.project.every.databinding.ActivitySignUpBinding
import com.project.every.viewmodel.signup.SignUpViewModel

class SignUpActivity : BaseActivity() {

    lateinit var binding : ActivitySignUpBinding
    lateinit var viewModel : SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@SignUpActivity, R.layout.activity_sign_up)
        viewModel = ViewModelProviders.of(this@SignUpActivity).get(SignUpViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SignUpActivity

        toolbarInit(binding.toolbar)
    }

    override fun observerViewModel(){
        with(viewModel){
            onCheckEvent.observe(this@SignUpActivity, Observer {
                if(viewModel.checkInfo.value == 0){
                    SignUpData.identityData = viewModel.checkInfo.value!!
                    startActivity(Intent(this@SignUpActivity, Email_PwSignUpActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }else toastMessage(applicationContext, "현재 개발 진행중인 기능입니다.")
            })
        }
    }
}
