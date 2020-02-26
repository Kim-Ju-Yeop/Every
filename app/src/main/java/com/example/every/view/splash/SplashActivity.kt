package com.example.every.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.every.view.MainActivity
import com.example.every.R

class SplashActivity : AppCompatActivity() {

    val SPLASH_TIME : Long = 800

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setSplash()
    }

    // SplashActivity Setting
    fun setSplash(){
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()}, SPLASH_TIME)
    }
}
