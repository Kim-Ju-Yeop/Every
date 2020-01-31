package com.example.every.activity.signup.signup.data

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.databinding.ActivityEmailPasswordBinding
import com.example.every.databinding.ActivitySignUpBinding
import com.example.every.viewmodel.signup.SignUpViewModel
import com.example.every.viewmodel.signup.signup.data.Email_PasswordViewModel

class Email_PasswordActivity : AppCompatActivity() {

    lateinit var binding : ActivityEmailPasswordBinding
    lateinit var viewModel : Email_PasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email__password)

        binding = DataBindingUtil.setContentView(this@Email_PasswordActivity, R.layout.activity_email__password)
        viewModel = ViewModelProviders.of(this@Email_PasswordActivity).get(Email_PasswordViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@Email_PasswordActivity

        toolbarInit()
        FocusEditText()
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

    fun FocusEditText(){
        binding.emailEditText.setOnFocusChangeListener(object : View.OnFocusChangeListener{
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if(!hasFocus){
                    if(!viewModel.checkEmpty(binding.emailEditText.text.toString(), 0)){
                        binding.emailAnswer.visibility = View.GONE
                    }else{
                        if(!viewModel.checkType(binding.emailEditText.text.toString(), 0)){
                            binding.emailAnswer.visibility = View.GONE
                        } else{
                            binding.emailAnswer.visibility = View.VISIBLE
                        }
                    }
                }
            }
        })
        binding.pwEditText.setOnFocusChangeListener(object : View.OnFocusChangeListener{
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if(!hasFocus){
                    if(!viewModel.checkEmpty(binding.pwEditText.text.toString(), 1)){
                        binding.pwAnswer.visibility = View.GONE
                    }else{
                        if(!viewModel.checkType(binding.pwEditText.text.toString(), 1)){
                            binding.pwAnswer.visibility = View.GONE
                        } else{
                            binding.pwAnswer.visibility = View.VISIBLE
                        }
                    }
                }
            }
        })
    }
    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@Email_PasswordActivity, Observer {
                val checkIdentity = getSharedPreferences("checkIdentity", Context.MODE_PRIVATE)

                if(checkIdentity.getInt("identityData", 99) == 0){
                    viewModel.signUpActivity.signUpDataStudent.email = viewModel.email.value.toString()
                    viewModel.signUpActivity.signUpDataStudent.pw = viewModel.pw.value.toString()
                } else if(checkIdentity.getInt("identityData", 99) == 1) {
                    viewModel.signUpActivity.signUpDataWorker.email = viewModel.email.value.toString()
                    viewModel.signUpActivity.signUpDataWorker.pw = viewModel.pw.value.toString()
                }
                startActivity(Intent(this@Email_PasswordActivity, Name_BirthActivity::class.java))
            })
            onFailEvent.observe(this@Email_PasswordActivity, Observer {
                binding.emailAnswer.visibility = View.GONE
                binding.pwAnswer.visibility = View.GONE
                Toast.makeText(applicationContext, "이메일과 비밀번호를 다시 한 번 확인해주세요.", Toast.LENGTH_SHORT).show()
            })
        }
    }
}
