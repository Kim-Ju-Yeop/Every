package com.example.every.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.activity.signin.SignInActivity
import com.example.every.databinding.ActivityMainBinding
import com.example.every.viewmodel.MainViewModel

class MainActivity: AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var viewModel : MainViewModel

    lateinit var checkLogin : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this@MainActivity,
            R.layout.activity_main
        )
        viewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@MainActivity

        loginCheck()
        observerViewModel()
    }

    fun loginCheck(){
        checkLogin = getSharedPreferences("checkLogin", Context.MODE_PRIVATE)
        viewModel.checkLogin(checkLogin)
    }

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@MainActivity, Observer {
                // 메인 화면으로 이동
            })
            onFailEvent.observe(this@MainActivity, Observer {
                startActivity(Intent(this@MainActivity, SignInActivity::class.java))
                finish()
            })
        }
    }
}
