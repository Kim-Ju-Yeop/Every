package com.example.every.view.student.activity.more

import android.content.Context
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
import com.example.every.databinding.ActivityStudentAccountBinding
import com.example.every.view.signin.SignInActivity
import com.example.every.viewmodel.student.activity.more.StudentAccountViewModel

class StudentAccountActivity : BaseActivity() {

    lateinit var binding : ActivityStudentAccountBinding
    lateinit var viewModel : StudentAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_account)

        binding = DataBindingUtil.setContentView(this@StudentAccountActivity, R.layout.activity_student_account)
        viewModel = ViewModelProviders.of(this@StudentAccountActivity).get(StudentAccountViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@StudentAccountActivity

        viewModel.getStudentInfo(binding.name, binding.email, binding.phone, binding.school, binding.birth)
        toolbarInit(binding.toolbar)
    }

    override fun observerViewModel() {
       with(viewModel){
           onLogoutEvent.observe(this@StudentAccountActivity, Observer {
               val builder = AlertDialog.Builder(this@StudentAccountActivity)
               builder.setTitle("로그아웃").setMessage("정말 로그아웃을 하실 것입니까?")
               builder.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                   val loginData = applicationContext.getSharedPreferences("checkLogin", Context.MODE_PRIVATE)
                   val loginData_editor = loginData.edit()

                   loginData_editor.putBoolean("loginData", false)
                   loginData_editor.commit()

                   startActivity(Intent(this@StudentAccountActivity, SignInActivity::class.java))
                   finish()
               })
               builder.setNegativeButton("취소", DialogInterface.OnClickListener { dialog, which ->
                   toastMessage(applicationContext, "로그아웃을 하지 않았습니다.")
               })
               val alertDialog = builder.create()
               alertDialog.show()
           })
       }
    }
}
