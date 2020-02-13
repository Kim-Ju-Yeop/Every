package com.example.every.activity.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.activity.base.BaseSignUpActivity
import com.example.every.activity.signup.signup.data.Email_PasswordSignUpActivity
import com.example.every.databinding.ActivitySignUpBinding
import com.example.every.network.request.model.signup.SignUpDataStudent
import com.example.every.network.request.model.signup.SignUpDataWorker
import com.example.every.viewmodel.signup.SignUpSignUpViewModel

object SignUpData{
    val signUpDataStudent = SignUpDataStudent()
    val signUpDataWorker = SignUpDataWorker()
}

class SignUpSignUpActivity : BaseSignUpActivity() {

    lateinit var binding : ActivitySignUpBinding
    lateinit var viewModel : SignUpSignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding = DataBindingUtil.setContentView(this@SignUpSignUpActivity, R.layout.activity_sign_up)
        viewModel = ViewModelProviders.of(this@SignUpSignUpActivity).get(SignUpSignUpViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SignUpSignUpActivity

        toolbarInit(binding.toolbar)
        observerViewModel()
    }
    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@SignUpSignUpActivity, Observer {
                val identity = applicationContext.getSharedPreferences("checkIdentity", Context.MODE_PRIVATE)
                val editor = identity.edit()

                editor.putInt("identityData", viewModel.checkInfo.value!!)
                editor.commit()
                startActivity(Intent(this@SignUpSignUpActivity, Email_PasswordSignUpActivity::class.java))
            })
        }
    }
}
