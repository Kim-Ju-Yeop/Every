package com.example.every.view.signup.signup.data

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.base.BaseActivity
import com.example.every.base.SignUpData
import com.example.every.databinding.ActivitySchoolBinding
import com.example.every.viewmodel.signup.signup.data.SchoolSignUpViewModel

class SchoolSignUpActivity : BaseActivity() {

    lateinit var binding : ActivitySchoolBinding
    lateinit var viewModel : SchoolSignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@SchoolSignUpActivity, R.layout.activity_school)
        viewModel = ViewModelProviders.of(this@SchoolSignUpActivity).get(SchoolSignUpViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SchoolSignUpActivity

        toolbarInit(binding.toolbar)
    }

    override fun onResume() {
        super.onResume()
        val checkSchool = getSharedPreferences("checkSchool", Context.MODE_PRIVATE)
        viewModel.schoolNameSetting(checkSchool)
    }

    override fun observerViewModel(){
        with(viewModel){
            onSchoolSearchEvent.observe(this@SchoolSignUpActivity, Observer {
                startActivity(Intent(this@SchoolSignUpActivity, SchoolListSignUpActivity::class.java))
            })
            onSchoolEnableTrueEVent.observe(this@SchoolSignUpActivity, Observer {
                binding.schoolName.isSelected = true
                binding.nextButton.isEnabled = true
                binding.nextButton.setBackgroundResource(R.drawable.background_corners_gradient)
            })
            onSchoolEnableFalseEvent.observe(this@SchoolSignUpActivity, Observer {
                binding.schoolName.isSelected = true
                binding.nextButton.isEnabled = false
                binding.nextButton.setBackgroundResource(R.color.gray)
            })
            onSchoolNextEvent.observe(this@SchoolSignUpActivity, Observer {
                SignUpData.signUpDataStudent.school_id = viewModel.schoolId.value.toString()
                startActivity(Intent(applicationContext, SignUpFinishActivity::class.java))
            })
        }
    }
}
