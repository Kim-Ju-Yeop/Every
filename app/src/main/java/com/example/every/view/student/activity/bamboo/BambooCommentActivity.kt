package com.example.every.view.student.activity.bamboo

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.every.R
import com.example.every.view.student.activity.bamboo.adapter.BambooCommentAdapter
import com.example.every.base.BaseActivity
import com.example.every.base.StudentData
import com.example.every.databinding.ActivityBambooCommentBinding
import com.example.every.viewmodel.student.activity.bamboo.BambooCommentViewModel

class BambooCommentActivity : BaseActivity() {

    lateinit var binding : ActivityBambooCommentBinding
    lateinit var viewModel : BambooCommentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@BambooCommentActivity, R.layout.activity_bamboo_comment)
        viewModel = ViewModelProviders.of(this@BambooCommentActivity).get(BambooCommentViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@BambooCommentActivity

        refreshLayout()

        StudentData.postIdx.value = intent.extras!!.getInt("idx")
        viewModel.getBambooComment()
    }

    override fun onResume() {
        super.onResume()
        val intent = intent

        binding.idx.text = "#${intent.extras!!.getInt("idx")}번째 이야기"
        binding.timer.text = intent.extras!!.getString("timer")
        binding.content.text = intent.extras!!.getString("content")
        binding.comment.text = intent.extras!!.getString("comment")

        if(intent.extras!!.getString("created_at").equals("★ 오늘의 인기 게시글")){
            binding.createdAt.text = intent.extras!!.getString("created_at")
            binding.createdAt.setTextColor(Color.parseColor("#FFC400"))
            binding.createdAt.setBackgroundResource(R.drawable.create_at_background)
            binding.createdAt.setPadding(12)
        }else binding.createdAt.text = intent.extras!!.getString("created_at")

        StudentData.postIdx.value = intent.extras!!.getInt("idx")
        viewModel.getBambooComment()
    }

    private fun refreshLayout(){
        binding.swipeRefreshLayout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {
                viewModel.getBambooComment()
                Handler().postDelayed({ binding.swipeRefreshLayout.isRefreshing = false }, 500)
            }
        })
    }

    @SuppressLint("LongLogTag")
    override fun observerViewModel(){
        with(viewModel){
            onGetBambooCommentSuccessEvent.observe(this@BambooCommentActivity, Observer {
                val adapter = BambooCommentAdapter(applicationContext, viewModel.bambooCommentDataList)
                binding.recyclerView.adapter = adapter
            })
            onGetBambooCommentFailureEvent.observe(this@BambooCommentActivity, Observer {
                val adapter = BambooCommentAdapter(applicationContext, viewModel.bambooCommentDataList)
                binding.recyclerView.adapter = adapter

                Log.e("BambooCommentActivity[Error]", "게시글의 댓글이 존재하지 않습니다.")
            })
            onBambooCommentReplyEvent.observe(this@BambooCommentActivity, Observer {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.commentEditText.windowToken, 0)

                binding.commentEditText.text = null
                viewModel.getBambooComment()
            })
            onBambooCommentReplyEmptyEvent.observe(this@BambooCommentActivity, Observer {
                toastMessage(applicationContext, "댓글의 내용을 입력해주세요.")
            })
            onBambooBackEvent.observe(this@BambooCommentActivity, Observer {
                finish()
            })
            replyEditText.observe(this@BambooCommentActivity, Observer {
                if(replyEditText.value!!.length > 250) toastMessage(applicationContext, "250글자 내외로 작성할 수 있습니다.")
            })
        }
    }

    override fun onBackPressed() = finish()
}
