package com.example.every.view.student.activity.schedule

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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
        with(viewModel){
            onNextEvent.observe(this@ScheduleContentActivity, Observer {
                finish()
            })
            onTrashEvent.observe(this@ScheduleContentActivity, Observer {
                val builder = AlertDialog.Builder(this@ScheduleContentActivity)
                builder.setTitle("삭제").setMessage("일정을 정말 삭제하실 것입니까?")
                builder.setPositiveButton("삭제", DialogInterface.OnClickListener { dialog, which ->
                    viewModel.deleteSchedule()
                })
                builder.setNegativeButton("취소", DialogInterface.OnClickListener { dialog, which ->
                    // 일정 삭제 취소 이벤트
                })
                val alertDialog = builder.create()
                alertDialog.show()
            })
            onEditEvent.observe(this@ScheduleContentActivity, Observer {
                val intent = Intent(this@ScheduleContentActivity, ScheduleEditActivity::class.java)
                intent.putExtra("idx", viewModel.idx.value)
                intent.putExtra("title", binding.titleTextView.text.toString())
                intent.putExtra("content", binding.contentTextView.text.toString())
                intent.putExtra("start_date", "${binding.dateTextView.text.substring(0, 4)}-" +
                                                           "${binding.dateTextView.text.substring(6, 8)}-" +
                                                           "${binding.dateTextView.text.substring(10, 12)}")
                intent.putExtra("end_date", "${binding.dateTextView.text.substring(16, 20)}-" +
                                                         "${binding.dateTextView.text.substring(22, 24)}-" +
                                                         "${binding.dateTextView.text.substring(26, 28)}")
                startActivity(intent)
            })
        }
    }
}
