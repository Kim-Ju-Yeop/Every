package com.example.every.view.student.activity.bamboo

import android.app.Activity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.base.BaseActivity
import com.example.every.databinding.ActivityBambooPostBinding
import com.example.every.viewmodel.student.activity.bamboo.BambooPostViewModel

class BambooPostActivity : BaseActivity() {

    lateinit var binding : ActivityBambooPostBinding
    lateinit var viewModel : BambooPostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@BambooPostActivity, R.layout.activity_bamboo_post)
        viewModel = ViewModelProviders.of(this@BambooPostActivity).get(BambooPostViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@BambooPostActivity

        toolbarInit(binding.toolbar)
    }

    override fun observerViewModel(){
        with(viewModel){
            onBambooPostSuccessEvent.observe(this@BambooPostActivity, Observer {
                toastMessage(applicationContext, "정상적으로 글 작성을 수행하였습니다.")
                setResult(Activity.RESULT_OK)
                finish()
            })
            onBambooPostFailureEvent.observe(this@BambooPostActivity, Observer {
                toastMessage(applicationContext, "먼저 글을 작성해주시기 바랍니다.")
            })
            onBambooPostImageEvent.observe(this@BambooPostActivity, Observer {
                toastMessage(applicationContext, "현재 개발중인 기능입니다.")
            })
            content_EditText.observe(this@BambooPostActivity, Observer {
                if(content_EditText.value!!.length >= 500) toastMessage(applicationContext, "500글자 내외로 작성할 수 있습니다.")
            })
        }
    }
}
