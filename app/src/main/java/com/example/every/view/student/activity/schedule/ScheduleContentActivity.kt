package com.example.every.view.student.activity.schedule

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
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

    override fun onResume() {
        super.onResume()
        viewModel.getIdxSchedule()
    }

    private fun init(){
        val intent = intent
        binding.titleTextView.isSelected = true
        viewModel.idx.value = intent.extras!!.getInt("idx")
        viewModel.getIdxSchedule()
    }

    override fun observerViewModel() {
        with(viewModel){
            onTrashEvent.observe(this@ScheduleContentActivity, Observer {
                val builder = AlertDialog.Builder(this@ScheduleContentActivity)
                builder.setTitle("삭제").setMessage("일정을 정말 삭제하실 것입니까?")
                builder.setPositiveButton("삭제", DialogInterface.OnClickListener { dialog, which ->
                    viewModel.deleteSchedule()
                })
                builder.setNegativeButton("취소", DialogInterface.OnClickListener { dialog, which ->
                    toastMessage(applicationContext, "일정 삭제를 취소하였습니다.")
                })
                val alertDialog = builder.create()
                alertDialog.show()
            })
            onEditEvent.observe(this@ScheduleContentActivity, Observer {
                val intent = Intent(this@ScheduleContentActivity, ScheduleEditActivity::class.java)
                intent.putExtra("idx", viewModel.idx.value)
                startActivity(intent)
            })
            onBackEvent.observe(this@ScheduleContentActivity, Observer {
                finish()
            })
        }
    }
}
