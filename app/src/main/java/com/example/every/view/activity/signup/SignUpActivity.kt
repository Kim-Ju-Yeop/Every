package com.example.every.view.activity.signup

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.view.activity.signup.signup.data.Email_PasswordSignUpActivity
import com.example.every.base.BaseActivity
import com.example.every.base.SignUpData
import com.example.every.databinding.ActivitySignUpBinding
import com.example.every.viewmodel.signup.SignUpViewModel

class SignUpActivity : BaseActivity() {

    lateinit var binding : ActivitySignUpBinding
    lateinit var viewModel : SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding = DataBindingUtil.setContentView(this@SignUpActivity, R.layout.activity_sign_up)
        viewModel = ViewModelProviders.of(this@SignUpActivity).get(SignUpViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SignUpActivity

        toolbarInit(binding.toolbar)
        observerViewModel()
    }
    override fun observerViewModel(){
        with(viewModel){
            onNextEvent.observe(this@SignUpActivity, Observer {
                SignUpData.identityData = viewModel.checkInfo.value!!
                startActivity(Intent(this@SignUpActivity, Email_PasswordSignUpActivity::class.java))
            })
        }
    }
}
