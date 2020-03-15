package com.example.every.view.student.activity.bamboo

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.base.BaseActivity
import com.example.every.databinding.ActivityBambooCommentEditBinding
import com.example.every.viewmodel.student.activity.bamboo.BambooCommentEditViewModel

class BambooCommentEditActivity : BaseActivity() {

    lateinit var binding : ActivityBambooCommentEditBinding
    lateinit var viewModel : BambooCommentEditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@BambooCommentEditActivity, R.layout.activity_bamboo_comment_edit)
        viewModel = ViewModelProviders.of(this@BambooCommentEditActivity).get(BambooCommentEditViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@BambooCommentEditActivity

        toolbarInit(binding.toolbar)
    }

    override fun onResume() {
        super.onResume()
        val intent = intent
        viewModel.idx.value = intent.extras!!.getInt("idx")
        viewModel.comment_EditText.value = intent.extras!!.getString("content")
    }

    override fun observerViewModel() {
        with(viewModel){
            onBambooCommentReplySuccessEvent.observe(this@BambooCommentEditActivity, Observer {
                toastMessage(applicationContext, "댓글을 수정하였습니다.")
                onBackPressed()
            })
            onBambooCommentReplyEmptyEvent.observe(this@BambooCommentEditActivity, Observer {
                toastMessage(applicationContext, "수정하는 댓글의 내용을 입력해주세요.")
            })
            onBambooCommentEditImageEvent.observe(this@BambooCommentEditActivity, Observer {
                toastMessage(applicationContext, "현재 개발중인 기능입니다.")
            })
            comment_EditText.observe(this@BambooCommentEditActivity, Observer {
                if(comment_EditText.value!!.length >= 250) toastMessage(applicationContext, "250글자 내외로 작성할 수 있습니다.")
            })
        }
    }
}
