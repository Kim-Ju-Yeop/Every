package com.example.every.activity.student.bamboo

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.base.BaseActivity
import com.example.every.databinding.ActivityBambooPostBinding
import com.example.every.viewmodel.student.bamboo.activity.BambooPostViewModel

class BambooPostActivity : BaseActivity() {

    lateinit var binding : ActivityBambooPostBinding
    lateinit var viewModel : BambooPostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bamboo_post)

        binding = DataBindingUtil.setContentView(this@BambooPostActivity, R.layout.activity_bamboo_post)
        viewModel = ViewModelProviders.of(this@BambooPostActivity).get(BambooPostViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@BambooPostActivity

        toolbarInit(binding.toolbar)
        observerViewModel()
    }

    override fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@BambooPostActivity, Observer {
                Toast.makeText(applicationContext, "정상적으로 글 작성을 수행하였습니다.", Toast.LENGTH_SHORT).show()
                finish()
            })
            onFailEvent.observe(this@BambooPostActivity, Observer {
                Toast.makeText(applicationContext, "먼저 글을 작성해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
            })
            onImageEvent.observe(this@BambooPostActivity, Observer {
                Toast.makeText(applicationContext, "아직 사진과 비디오 추가 기능을 제공하지 않습니다.", Toast.LENGTH_SHORT).show()
            })
            content_EditText.observe(this@BambooPostActivity, Observer {
                if(content_EditText.value!!.length >= 500) toastMessage(applicationContext, "500글자 내외로 작성할 수 있습니다.")
            })
        }
    }
}
