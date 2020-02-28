package com.example.every.view.signin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.view.signup.SignUpActivity
import com.example.every.view.student.activity.StudentMainActivity
import com.example.every.base.BaseActivity
import com.example.every.base.BaseFragment
import com.example.every.base.StudentData
import com.example.every.databinding.ActivitySignInBinding
import com.example.every.viewmodel.signin.SignInViewModel
import kotlinx.android.synthetic.main.bamboo_item.*

class SignInActivity : BaseActivity() {

    lateinit var binding : ActivitySignInBinding
    lateinit var viewModel : SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        binding = DataBindingUtil.setContentView(this@SignInActivity, R.layout.activity_sign_in)
        viewModel = ViewModelProviders.of(this@SignInActivity).get(SignInViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SignInActivity

        observerViewModel()
    }

    override fun observerViewModel(){
        with(viewModel){
            onSignUpEvent.observe(this@SignInActivity, Observer {
                startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
            })
            onLostPwEvent.observe(this@SignInActivity, Observer {
                Toast.makeText(applicationContext, "아직 비밀번호 찾기 기능은 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
            })
            onErrorEvent.observe(this@SignInActivity, Observer {
                Toast.makeText(applicationContext, "이메일 또는 비밀번호에 형식 오류가 있습니다.", Toast.LENGTH_SHORT).show()
            })
            onFailEvent.observe(this@SignInActivity, Observer {
                Toast.makeText(applicationContext, "이메일 또는 비밀번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
            })
            onSuccessEvent.observe(this@SignInActivity, Observer {

                // SignInData Setting
                val loginData = applicationContext.getSharedPreferences("checkLogin", Context.MODE_PRIVATE)
                val loginData_editor = loginData.edit()

                loginData_editor.putBoolean("loginData", true)
                loginData_editor.commit()

                // Token Setting
                val token = applicationContext.getSharedPreferences("checkToken", Context.MODE_PRIVATE)
                val token_editor = token.edit()

                token_editor.putString("tokenData", viewModel.token.value)
                token_editor.commit()

                val identity = applicationContext.getSharedPreferences("checkIdentity", Context.MODE_PRIVATE)
                val identity_editor = identity.edit()

                if(viewModel.worker_idx.value != null){
                    identity_editor.putString("identityData", "worker")
                    identity_editor.putInt("identityIdx_Worker", viewModel.worker_idx.value!!)
                    identity_editor.commit()

                   // 직장인 페이지 이동
                }else if(viewModel.student_idx.value != null){
                    identity_editor.putString("identityData", "student")
                    identity_editor.putInt("identityIdx_Student", viewModel.student_idx.value!!)
                    identity_editor.commit()

                    startActivity(Intent(this@SignInActivity, StudentMainActivity::class.java))
                    finish()
                }
            })
        }
    }
}
