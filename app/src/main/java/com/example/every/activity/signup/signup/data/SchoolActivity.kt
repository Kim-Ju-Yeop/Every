package com.example.every.activity.signup.signup.data

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.activity.base.BaseActivity
import com.example.every.activity.signup.SignUpData
import com.example.every.databinding.ActivitySchoolBinding
import com.example.every.viewmodel.signup.signup.data.SchoolViewModel

class SchoolActivity : BaseActivity() {

    lateinit var binding : ActivitySchoolBinding
    lateinit var viewModel : SchoolViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school)

        binding = DataBindingUtil.setContentView(this@SchoolActivity, R.layout.activity_school)
        viewModel = ViewModelProviders.of(this@SchoolActivity).get(SchoolViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SchoolActivity

        toolbarInit(binding.toolbar)
        observerViewModel()
    }

    // Activity 새로 보이는 생명 주기
    override fun onResume() {
        super.onResume()
        checkSchoolData()
    }

    fun checkSchoolData(){
        val checkSchool = getSharedPreferences("checkSchool", Context.MODE_PRIVATE)
        viewModel.schoolNameSetting(checkSchool)
    }

    fun observerViewModel(){
        with(viewModel){
            onSearchEvent.observe(this@SchoolActivity, Observer {
                startActivity(Intent(this@SchoolActivity, SchoolListActivity::class.java))
            })
            onEnableTrueEVent.observe(this@SchoolActivity, Observer {
                binding.schoolName.setSingleLine(true)
                binding.schoolName.ellipsize = TextUtils.TruncateAt.MARQUEE
                binding.schoolName.isSelected = true

                binding.nextButton.isEnabled = true
                binding.nextButton.setBackgroundResource(R.drawable.background_corners_gradient)
            })
            onEnableFalseEvent.observe(this@SchoolActivity, Observer {
                binding.schoolName.setSingleLine(true)
                binding.schoolName.ellipsize = TextUtils.TruncateAt.MARQUEE
                binding.schoolName.isSelected = true

                binding.nextButton.isEnabled = false
                binding.nextButton.setBackgroundResource(R.color.gray)
            })
            onSuccessEvent.observe(this@SchoolActivity, Observer {
                SignUpData.signUpDataStudent.school_id = viewModel.schoolId.value.toString()
                startActivity(Intent(applicationContext, SignUpFinishActivity::class.java))
            })
        }
    }
}
