package com.example.every.activity.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        }
    }
}
