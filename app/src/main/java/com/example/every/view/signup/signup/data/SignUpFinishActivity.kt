package com.example.every.view.signup.signup.data

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.view.signin.SignInActivity
import com.example.every.view.signup.SignUpActivity
import com.example.every.base.BaseActivity
import com.example.every.base.SignUpData
import com.example.every.databinding.ActivitySignUpFinishBinding
import com.example.every.viewmodel.signup.signup.data.SignUpFinishViewModel

class SignUpFinishActivity : BaseActivity() {

    lateinit var binding : ActivitySignUpFinishBinding
    lateinit var viewModel :SignUpFinishViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_finish)

        binding = DataBindingUtil.setContentView(this@SignUpFinishActivity, R.layout.activity_sign_up_finish)
        viewModel = ViewModelProviders.of(this@SignUpFinishActivity).get(SignUpFinishViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SignUpFinishActivity

        register()
        observerViewModel()
    }

    fun register(){
        viewModel.firstText.value = "회원가입 완료"
        viewModel.secondText.value = "정상적으로 회원가입이 완료되었습니다!"

        viewModel.signUp(SignUpData.identityData!!)
    }

    override fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@SignUpFinishActivity, Observer {
                binding.nextButton.text = "로그인 하기"
            })
            onFailEvent.observe(this@SignUpFinishActivity, Observer {
                binding.nextButton.text = "회원가입 다시 진행하기"
            })
            onNextEvent.observe(this@SignUpFinishActivity, Observer {
                if(binding.nextButton.text.equals("로그인 하기")){
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
