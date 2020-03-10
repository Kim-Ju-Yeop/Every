package com.example.every.view.student.activity.schedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.base.BaseActivity
import com.example.every.databinding.ActivityScheduleContentBinding
import com.example.every.viewmodel.student.activity.schedule.ScheduleContentViewModel

class ScheduleContentActivity : BaseActivity() {

    lateinit var binding : ActivityScheduleContentBinding
    lateinit var viewModel : ScheduleContentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@ScheduleContentActivity, R.layout.activity_schedule_content)
        viewModel = ViewModelProviders.of(this@ScheduleContentActivity).get(ScheduleContentViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@ScheduleContentActivity

        init()
    }

    private fun init(){
        val intent = intent
        viewModel.idx.value = intent.extras!!.getInt("idx")
        binding.titleTextView.text = intent.extras!!.getString("title")
        binding.contentTextView.text = intent.extras!!.getString("content")
        binding.dateTextView.text = intent.extras!!.getString("date")
    }

    override fun observerViewModel() {

    }
}
