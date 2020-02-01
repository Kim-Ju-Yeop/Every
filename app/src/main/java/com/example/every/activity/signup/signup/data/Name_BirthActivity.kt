package com.example.every.activity.signup.signup.data

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.example.every.databinding.ActivityNameBirthBinding
import com.example.every.viewmodel.signup.signup.data.Name_BirthViewModel

class Name_BirthActivity : BaseActivity() {

    lateinit var binding : ActivityNameBirthBinding
    lateinit var viewModel : Name_BirthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name__birth)

        binding = DataBindingUtil.setContentView(this@Name_BirthActivity, R.layout.activity_name__birth)
        viewModel = ViewModelProviders.of(this@Name_BirthActivity).get(Name_BirthViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@Name_BirthActivity

        toolbarInit(binding.toolbar)
        birthCheck()
        observerViewModel()
    }
    fun birthCheck(){
        binding.birthEditText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(viewModel.checkType(binding.birthEditText.text.toString())){
                    if(viewModel.birth_check.value == null){
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
            onSuccessEvent.observe(this@Name_BirthActivity, Observer {
                val checkIdentity = getSharedPreferences("checkIdentity", Context.MODE_PRIVATE)
                val identityData = checkIdentity.getInt("identityData", 99)

                if(identityData == 0){
                    SignUpData.signUpDataStudent.name = viewModel.name.value.toString()
                    SignUpData.signUpDataStudent.birth_year = Integer.parseInt(viewModel.birth.value.toString())
                } else if(identityData == 1) {
                    SignUpData.signUpDataWorker.name = viewModel.name.value.toString()
                    SignUpData.signUpDataWorker.birth_year = Integer.parseInt(viewModel.birth.value.toString())
                }
                startActivity(Intent(this@Name_BirthActivity, PhoneActivity::class.java))
            })
        }
    }
}
