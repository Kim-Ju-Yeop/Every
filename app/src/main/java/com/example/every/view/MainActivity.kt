package com.example.every.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.every.R
import com.example.every.view.signin.SignInActivity
import com.example.every.view.student.activity.StudentMainActivity

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkLogin()
    }

    private fun checkLogin(){
        val checkLogin : SharedPreferences = getSharedPreferences("checkLogin", Context.MODE_PRIVATE)
        if(checkLogin.getBoolean("loginData", false)){
            val checkIdentity : SharedPreferences = getSharedPreferences("checkIdentity", Context.MODE_PRIVATE)

            if(checkIdentity.getString("identityData", null).equals("worker")){
                // 직장인 페이지 이동
            }else if(checkIdentity.getString("identityData", null).equals("student")){
                startActivity(Intent(this@MainActivity, StudentMainActivity::class.java))
                finish()
            }
        } else {
            startActivity(Intent(this@MainActivity, SignInActivity::class.java))
            finish()
        }
    }
}
