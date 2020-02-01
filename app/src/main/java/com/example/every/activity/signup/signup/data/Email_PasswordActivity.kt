package com.example.every.activity.signup.signup.data

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.activity.base.BaseActivity
import com.example.every.activity.signup.SignUpData
import com.example.every.databinding.ActivityEmailPasswordBinding
import com.example.every.databinding.ActivitySignUpBinding
import com.example.every.viewmodel.signup.SignUpViewModel
import com.example.every.viewmodel.signup.signup.data.Email_PasswordViewModel
import java.util.regex.Pattern

class Email_PasswordActivity : BaseActivity() {

    lateinit var binding : ActivityEmailPasswordBinding
    lateinit var viewModel : Email_PasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email__password)

        binding = DataBindingUtil.setContentView(this@Email_PasswordActivity, R.layout.activity_email__password)
        viewModel = ViewModelProviders.of(this@Email_PasswordActivity).get(Email_PasswordViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@Email_PasswordActivity

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
            onSuccessEvent.observe(this@Email_PasswordActivity, Observer {
                val checkIdentity = getSharedPreferences("checkIdentity", Context.MODE_PRIVATE)
                val identityData = checkIdentity.getInt("identityData", 99)

                if(identityData == 0){
                    SignUpData.signUpDataStudent.email = viewModel.email.value.toString()
                    SignUpData.signUpDataStudent.pw = viewModel.pw.value.toString()
                } else if(identityData == 1) {
                    SignUpData.signUpDataWorker.email = viewModel.email.value.toString()
                    SignUpData.signUpDataWorker.pw = viewModel.pw.value.toString()
                }
                startActivity(Intent(this@Email_PasswordActivity, Name_BirthActivity::class.java))
            })
        }
    }
}
