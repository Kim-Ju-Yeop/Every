package com.example.every.activity.signup.signup.data

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.activity.base.BaseSignUpActivity
import com.example.every.activity.base.IdentityData
import com.example.every.activity.signup.SignUpData
import com.example.every.databinding.ActivityEmailPasswordBinding
import com.example.every.viewmodel.signup.signup.data.Email_PasswordSignUpViewModel

class Email_PasswordSignUpActivity : BaseSignUpActivity() {

    lateinit var binding : ActivityEmailPasswordBinding
    lateinit var viewModel : Email_PasswordSignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email__password)

        binding = DataBindingUtil.setContentView(this@Email_PasswordSignUpActivity, R.layout.activity_email__password)
        viewModel = ViewModelProviders.of(this@Email_PasswordSignUpActivity).get(Email_PasswordSignUpViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@Email_PasswordSignUpActivity

        toolbarInit(binding.toolbar)
        emailCheck()
        pwCheck()
        observerViewModel()
    }
    fun emailCheck(){
        binding.emailEditText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?){}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int){}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(viewModel.checkType(binding.emailEditText.text.toString(), 0)){
                    viewModel.overlapEmail(binding.emailEditText.text.toString())
                }
            }
        })
    }
    fun pwCheck(){
        binding.pwEditText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?){}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int){}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(viewModel.checkType(binding.pwEditText.text.toString(), 1)){
                    if(viewModel.email_check.value == null && viewModel.pw_check.value == null){
                        binding.nextButton.setBackgroundResource(R.drawable.background_corners_gradient)
                        binding.nextButton.isEnabled = true
                    } else{
                        binding.nextButton.setBackgroundResource(R.color.gray)
                        binding.nextButton.isEnabled = false
                    }
                }else{
                    binding.nextButton.setBackgroundResource(R.color.gray)
                    binding.nextButton.isEnabled = false
                }
            }
        })
    }
    fun observerViewModel(){
        with(viewModel){
            onNextEvent.observe(this@Email_PasswordSignUpActivity, Observer {
                if(IdentityData.identityData == 0){
                    SignUpData.signUpDataStudent.email = viewModel.email.value.toString()
                    SignUpData.signUpDataStudent.pw = viewModel.pw.value.toString()
                } else if(IdentityData.identityData == 1) {
                    SignUpData.signUpDataWorker.email = viewModel.email.value.toString()
                    SignUpData.signUpDataWorker.pw = viewModel.pw.value.toString()
                }
                startActivity(Intent(this@Email_PasswordSignUpActivity, Name_BirthSignUpActivity::class.java))
            })
        }
    }
}
