package com.example.every.activity.signup.signup.data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.databinding.ActivitySchoolListBinding
import com.example.every.viewmodel.signup.signup.data.SchoolListViewModel

class SchoolListActivity : AppCompatActivity() {

    lateinit var binding : ActivitySchoolListBinding
    lateinit var viewModel : SchoolListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_school_list)

        binding = DataBindingUtil.setContentView(this@SchoolListActivity, R.layout.activity_school_list)
        viewModel = ViewModelProviders.of(this@SchoolListActivity).get(SchoolListViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SchoolListActivity

        observerViewModel()
    }

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@SchoolListActivity, Observer {
                // 성공
                Log.e("test", "성공")
            })
            onNotFoundEvent.observe(this@SchoolListActivity, Observer {
                // 학교 없음
                Log.e("test", "학교 없음")
            })
            onErrorEvent.observe(this@SchoolListActivity, Observer {
                // 서버 오류
                Log.e("test", "서버 오류")
            })
            onServerConnectErrorEvent.observe(this@SchoolListActivity, Observer {
                // 서버 통신 오류
                Log.e("test", "서버 통신 오류")
            })
        }
    }
}
