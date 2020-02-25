package com.example.every.activity.student.bamboo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.activity.student.bamboo.adapter.BambooCommentAdapter
import com.example.every.base.view.student.BaseStudentActivity
import com.example.every.base.view.student.idxData
import com.example.every.databinding.ActivityBambooCommentBinding
import com.example.every.viewmodel.student.bamboo.activity.BambooCommentViewModel

class BambooCommentActivity : BaseStudentActivity() {

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

    // Activity 새로 보이는 생명 주기
    override fun onResume() {
        super.onResume()
        val intent = intent

        binding.idx.text = "#${intent.extras!!.getInt("idx")}번째 이야기"
        binding.createdAt.text = intent.extras!!.getString("created_at")
        binding.timer.text = intent.extras!!.getString("timer")
        binding.content.text = intent.extras!!.getString("content")
        binding.comment.text = intent.extras!!.getString("comment")

        idxData.idx.value = intent.extras!!.getInt("idx")
        viewModel.getBambooComment()
    }

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@BambooCommentActivity, Observer {
                binding.recyclerView.visibility = View.VISIBLE
                binding.questionLayout.visibility = View.GONE

                val adapter = BambooCommentAdapter(applicationContext, viewModel.bambooCommentDataList)
                binding.recyclerView.adapter = adapter
            })
            onFailEvent.observe(this@BambooCommentActivity, Observer {
                binding.recyclerView.visibility = View.GONE
                binding.questionLayout.visibility = View.VISIBLE
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
