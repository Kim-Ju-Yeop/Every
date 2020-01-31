package com.example.every.activity.signup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.activity.signup.signup.data.Email_PasswordActivity
import com.example.every.databinding.ActivitySignUpBinding
import com.example.every.network.request.model.signup.SignUpDataStudent
import com.example.every.network.request.model.signup.SignUpDataWorker
import com.example.every.viewmodel.signin.SignInViewModel
import com.example.every.viewmodel.signup.SignUpViewModel

class SignUpActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignUpBinding
    lateinit var viewModel : SignUpViewModel

    val signUpDataStudent = SignUpDataStudent()
    val signUpDataWorker = SignUpDataWorker()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding = DataBindingUtil.setContentView(this@SignUpActivity, R.layout.activity_sign_up)
        viewModel = ViewModelProviders.of(this@SignUpActivity).get(SignUpViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SignUpActivity

        toolbarInit()
        observerViewModel()
    }

    fun toolbarInit(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@SignUpActivity, Observer {

                // CheckInfo 정보 SharedPreferences
                val identity = applicationContext.getSharedPreferences("checkIdentity", Context.MODE_PRIVATE)
                val editor = identity.edit()

                editor.putInt("identityData", viewModel.checkInfo.value!!)
                editor.commit()
                startActivity(Intent(this@SignUpActivity, Email_PasswordActivity::class.java))
            })
        }
    }
}
