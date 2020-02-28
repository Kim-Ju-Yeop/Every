package com.example.every.view.student.activity.bamboo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.base.BaseActivity
import com.example.every.databinding.ActivityBambooCommentEditBinding
import com.example.every.viewmodel.student.activity.BambooCommentEditViewModel
import kotlinx.android.synthetic.main.bamboo_item.*

class BambooCommentEditActivity : BaseActivity() {

    lateinit var binding : ActivityBambooCommentEditBinding
    lateinit var viewModel : BambooCommentEditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bamboo_comment_edit)

        binding = DataBindingUtil.setContentView(this@BambooCommentEditActivity, R.layout.activity_bamboo_comment_edit)
        viewModel = ViewModelProviders.of(this@BambooCommentEditActivity).get(BambooCommentEditViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@BambooCommentEditActivity

        toolbarInit(binding.toolbar)
        observerViewModel()
    }

    override fun onResume() {
        super.onResume()
        val intent = intent
        viewModel.idx.value = intent.extras!!.getInt("idx")
        viewModel.comment_EditText.value = intent.extras!!.getString("content")
    }

    override fun observerViewModel() {
        with(viewModel){
            onSuccessEvent.observe(this@BambooCommentEditActivity, Observer {
                Toast.makeText(applicationContext, "댓글을 수정하였습니다.", Toast.LENGTH_SHORT).show()
                finish()
            })
            onImageEvent.observe(this@BambooCommentEditActivity, Observer {
                Toast.makeText(applicationContext, "아직 사진과 비디오 추가 기능을 제공하지 않습니다.", Toast.LENGTH_SHORT).show()
            })
            comment_EditText.observe(this@BambooCommentEditActivity, Observer {
                if(comment_EditText.value!!.length >= 250) toastMessage(applicationContext, "250글자 내외로 작성할 수 있습니다.")
            })
        }
    }
}
