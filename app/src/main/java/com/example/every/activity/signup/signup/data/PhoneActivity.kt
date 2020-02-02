package com.example.every.activity.signup.signup.data

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.activity.base.BaseActivity
import com.example.every.activity.signup.SignUpData
import com.example.every.databinding.ActivityPhoneBinding
import com.example.every.viewmodel.signup.signup.data.PhoneViewModel
import kotlinx.android.synthetic.main.activity_phone.*

class PhoneActivity : BaseActivity() {

    lateinit var binding : ActivityPhoneBinding
    lateinit var viewModel : PhoneViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)

        binding = DataBindingUtil.setContentView(this@PhoneActivity, R.layout.activity_phone)
        viewModel = ViewModelProviders.of(this@PhoneActivity).get(PhoneViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@PhoneActivity

        toolbarInit(binding.toolbar)
        phoneCheck()
        observerViewModel()
    }
    fun phoneCheck(){
        binding.phoneEditText.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        binding.phoneEditText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(viewModel.checkType(binding.phoneEditText.text.toString())){
                    viewModel.overlapPhone(binding.phoneEditText.text.toString())
                }else{
                    binding.nextButton.setBackgroundResource(R.color.gray)
                    binding.nextButton.isEnabled = false
                }
            }
        })
    }
    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@PhoneActivity, Observer {
                val checkIdentity = getSharedPreferences("checkIdentity", Context.MODE_PRIVATE)
                val identityData = checkIdentity.getInt("identityData", 99)

                if(identityData == 0){
                    SignUpData.signUpDataStudent.phone = viewModel.phone.value.toString()
                    startActivity(Intent(this@PhoneActivity, SchoolActivity::class.java))
                } else if(identityData == 1) {
                    SignUpData.signUpDataWorker.phone = viewModel.phone.value.toString()
                    startActivity(Intent(this@PhoneActivity, WorkerActivity::class.java))
                }
            })
            on200Event.observe(this@PhoneActivity, Observer {
                if(viewModel.phone_check.value == null){
                    binding.nextButton.setBackgroundResource(R.drawable.background_corners_gradient)
                    binding.nextButton.isEnabled = true
                }
            })
            on409Event.observe(this@PhoneActivity, Observer {
                if(viewModel.phone_check.value != null){
                    binding.nextButton.setBackgroundResource(R.color.gray)
                    binding.nextButton.isEnabled = false
                }
            })
        }
    }
}
