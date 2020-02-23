package com.example.every.activity.student.bamboo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.activity.student.bamboo.adapter.BambooCommentAdapter
import com.example.every.databinding.ActivityBambooCommentBinding
import com.example.every.viewmodel.student.bamboo.activity.BambooCommentViewModel

class BambooCommentActivity : AppCompatActivity() {

    lateinit var binding : ActivityBambooCommentBinding
    lateinit var viewModel : BambooCommentViewModel

    var postIdx : Int = 0

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

        postIdx = intent.extras!!.getInt("idx")
        viewModel.getBambooComment(postIdx)
    }

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@BambooCommentActivity, Observer {
                val adapter =
                    BambooCommentAdapter(
                        applicationContext,
                        viewModel.bambooCommentList
                    )
                binding.recyclerView.adapter = adapter
            })
            onFailEvent.observe(this@BambooCommentActivity, Observer {

            })
            onPostEvent.observe(this@BambooCommentActivity, Observer {
                viewModel.postBambooReply(postIdx)
            })
            onSuccessEvent2.observe(this@BambooCommentActivity, Observer {
                viewModel.getBambooComment(postIdx)
            })
            onFailEvent2.observe(this@BambooCommentActivity, Observer {
                Toast.makeText(applicationContext, "내용을 입력해주세요!", Toast.LENGTH_SHORT).show()
            })
        }
    }
}
