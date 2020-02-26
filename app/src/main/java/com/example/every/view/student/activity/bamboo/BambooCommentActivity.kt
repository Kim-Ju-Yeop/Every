package com.example.every.view.student.activity.bamboo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.view.student.activity.bamboo.adapter.BambooCommentAdapter
import com.example.every.base.BaseActivity
import com.example.every.base.StudentData
import com.example.every.databinding.ActivityBambooCommentBinding
import com.example.every.viewmodel.student.activity.BambooCommentViewModel

class BambooCommentActivity : BaseActivity() {

    lateinit var binding : ActivityBambooCommentBinding
    lateinit var viewModel : BambooCommentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bamboo_comment)

        binding = DataBindingUtil.setContentView(this@BambooCommentActivity, R.layout.activity_bamboo_comment)
        viewModel = ViewModelProviders.of(this@BambooCommentActivity).get(BambooCommentViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@BambooCommentActivity

        observerViewModel()
    }

    override fun onResume() {
        super.onResume()
        val intent = intent

        binding.idx.text = "#${intent.extras!!.getInt("idx")}번째 이야기"
        binding.createdAt.text = intent.extras!!.getString("created_at")
        binding.timer.text = intent.extras!!.getString("timer")
        binding.content.text = intent.extras!!.getString("content")
        binding.comment.text = intent.extras!!.getString("comment")

        StudentData.postIdx.value = intent.extras!!.getInt("idx")
        viewModel.getBambooComment()
    }

    override fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@BambooCommentActivity, Observer {
                val adapter = BambooCommentAdapter(applicationContext, viewModel.bambooCommentDataList)
                binding.recyclerView.adapter = adapter
            })
            onFailEvent.observe(this@BambooCommentActivity, Observer {
                Toast.makeText(applicationContext, "댓글이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
            })
            onReplyEvent.observe(this@BambooCommentActivity, Observer {
                binding.commentEditText.text = null
                viewModel.getBambooComment()
            })
            onReplyEmptyEvent.observe(this@BambooCommentActivity, Observer {
                Toast.makeText(applicationContext, "내용을 입력해주세요!", Toast.LENGTH_SHORT).show()
            })
        }
    }
}
