package com.example.every.activity.signup.signup.data

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.activity.signin.SignInActivity
import com.example.every.activity.signup.SignUpActivity
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

        observerViewModel()
        register()
    }

    fun register(){
        val checkIdentity = getSharedPreferences("checkIdentity", Context.MODE_PRIVATE)
        val identityData = checkIdentity.getInt("identityData", 99)
        viewModel.signUp(identityData)
    }

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@SignUpFinishActivity, Observer {
                binding.nextButton.text = "Every 사용하기"
            })
            onFailEvent.observe(this@SignUpFinishActivity, Observer {
                binding.nextButton.text = "회원가입 다시 진행하기"
            })
            onButtonClickEvent.observe(this@SignUpFinishActivity, Observer {
                if(binding.nextButton.text.equals("Every 사용하기")){
                    startActivity(Intent(this@SignUpFinishActivity, SignInActivity::class.java))
                    finish()
                } else{
                    startActivity(Intent(this@SignUpFinishActivity, SignUpActivity::class.java))
                    finish()
                }
            })
        }
    }
}
