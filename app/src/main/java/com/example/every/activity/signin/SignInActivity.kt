package com.example.every.activity.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.activity.signup.SignUpActivity
import com.example.every.databinding.ActivitySignInBinding
import com.example.every.viewmodel.signin.SignInViewModel

class SignInActivity : AppCompatActivity() {

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

    fun observerViewModel(){
        with(viewModel){
            onSignUpEvent.observe(this@SignInActivity, Observer {
                startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
            })
            onLostPwEvent.observe(this@SignInActivity, Observer {
                Toast.makeText(applicationContext, "아직 비밀번호 찾기 기능은 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
            })
        }
    }
}
