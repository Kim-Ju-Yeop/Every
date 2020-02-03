package com.example.every.activity.signup.signup.data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.databinding.ActivitySignUpFinishBinding
import com.example.every.viewmodel.signup.signup.data.SignUpFinishViewModel

class SignUpFinishActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignUpFinishBinding
    lateinit var viewModel :SignUpFinishViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_finish)

        binding = DataBindingUtil.setContentView(this@SignUpFinishActivity, R.layout.activity_sign_up_finish)
        viewModel = ViewModelProviders.of(this@SignUpFinishActivity).get(SignUpFinishViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SignUpFinishActivity
    }
}
