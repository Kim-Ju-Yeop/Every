package com.project.every.view.student.activity.bamboo

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.project.every.R
import com.project.every.base.BaseActivity
import com.project.every.databinding.ActivityBambooMoreBinding
import com.project.every.viewmodel.student.activity.bamboo.BambooMoreViewModel

class BambooMoreActivity : BaseActivity() {

    lateinit var binding : ActivityBambooMoreBinding
    lateinit var viewModel : BambooMoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setGravity(Gravity.BOTTOM)

        binding = DataBindingUtil.setContentView(this@BambooMoreActivity, R.layout.activity_bamboo_more)
        viewModel = ViewModelProviders.of(this@BambooMoreActivity).get(BambooMoreViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@BambooMoreActivity
    }

    override fun onResume() {
        super.onResume()
        val intent = intent
        viewModel.idx.value = intent.extras!!.getInt("idx")
        viewModel.content.value = intent.extras!!.getString("content").toString()
    }

    override fun observerViewModel() {
        with(viewModel){
            onBambooMoreNextEvent.observe(this@BambooMoreActivity, Observer {
                val intent = Intent(this@BambooMoreActivity, BambooCommentEditActivity::class.java)
                intent.putExtra("idx", viewModel.idx.value)
                intent.putExtra("content", viewModel.content.value)

                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                finish()
            })
            onBambooMoreSuccessEvent.observe(this@BambooMoreActivity, Observer {
                Toast.makeText(applicationContext, "댓글이 정상적으로 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            })
        }
    }

    override fun onBackPressed() = finish()
}
