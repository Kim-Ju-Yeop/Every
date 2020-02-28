package com.example.every.view.student.activity.bamboo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.base.BaseActivity
import com.example.every.databinding.ActivityBambooMoreBinding
import com.example.every.viewmodel.student.activity.BambooMoreViewModel

class BambooMoreActivity : BaseActivity() {

    lateinit var binding : ActivityBambooMoreBinding
    lateinit var viewModel : BambooMoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setGravity(Gravity.BOTTOM)
        setContentView(R.layout.activity_bamboo_more)

        binding = DataBindingUtil.setContentView(this@BambooMoreActivity, R.layout.activity_bamboo_more)
        viewModel = ViewModelProviders.of(this@BambooMoreActivity).get(BambooMoreViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@BambooMoreActivity

        observerViewModel()
    }

    override fun onResume() {
        super.onResume()
        val intent = intent
        viewModel.idx.value = intent.extras!!.getInt("idx")
        viewModel.content.value = intent.extras!!.getString("content").toString()
    }

    override fun observerViewModel() {
        with(viewModel){
            onNextEvent.observe(this@BambooMoreActivity, Observer {
                val intent = Intent(this@BambooMoreActivity, BambooCommentEditActivity::class.java)
                intent.putExtra("idx", viewModel.idx.value)
                intent.putExtra("content", viewModel.content.value)

                startActivity(intent)
                finish()
            })
            onSuccessEvent.observe(this@BambooMoreActivity, Observer {
                Toast.makeText(applicationContext, "댓글이 정상적으로 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            })
        }
    }
}
