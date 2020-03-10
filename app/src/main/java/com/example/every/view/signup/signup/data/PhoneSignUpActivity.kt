package com.example.every.view.signup.signup.data

import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.base.BaseActivity
import com.example.every.base.SignUpData
import com.example.every.databinding.ActivityPhoneBinding
import com.example.every.viewmodel.signup.signup.data.PhoneSignUpViewModel

class PhoneSignUpActivity : BaseActivity(){

    lateinit var binding : ActivityPhoneBinding
    lateinit var viewModel : PhoneSignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)

        binding = DataBindingUtil.setContentView(this@PhoneSignUpActivity, R.layout.activity_phone)
        viewModel = ViewModelProviders.of(this@PhoneSignUpActivity).get(PhoneSignUpViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@PhoneSignUpActivity

        phoneCheck()
        toolbarInit(binding.toolbar)
    }

    private fun phoneCheck(){
        binding.phoneEditText.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        binding.phoneEditText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(viewModel.checkType(binding.phoneEditText.text.toString()))
                    viewModel.getOverlapPhone(binding.phoneEditText.text.toString())
                else{
                    binding.nextButton.setBackgroundResource(R.color.gray)
                    binding.nextButton.isEnabled = false
                }
            }
        })
    }

    override fun observerViewModel(){
        with(viewModel){
            onPhoneSuccessEvent.observe(this@PhoneSignUpActivity, Observer {
                if(viewModel.phone_check.value == null){
                    binding.nextButton.setBackgroundResource(R.drawable.background_corners_gradient)
                    binding.nextButton.isEnabled = true
                }
            })
            onPhoneFailureEvent.observe(this@PhoneSignUpActivity, Observer {
                if(viewModel.phone_check.value != null){
                    binding.nextButton.setBackgroundResource(R.color.gray)
                    binding.nextButton.isEnabled = false
                }
            })
            onPhoneNextEvent.observe(this@PhoneSignUpActivity, Observer {
                if(SignUpData.identityData == 0){
                    SignUpData.signUpDataStudent.phone = viewModel.phone.value.toString()
                    startActivity(Intent(this@PhoneSignUpActivity, SchoolSignUpActivity::class.java))
                } else if(SignUpData.identityData == 1) {
                    SignUpData.signUpDataWorker.phone = viewModel.phone.value.toString()
                    startActivity(Intent(this@PhoneSignUpActivity, WorkerSignUpActivity::class.java))
                }
            })
        }
    }
}
