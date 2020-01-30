package com.example.every.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.example.every.MainActivity
import com.example.every.R
import com.example.every.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var binding : ActivitySplashBinding
    val SPLASH_TIME : Long = 2500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        binding = DataBindingUtil.setContentView(this@SplashActivity, R.layout.activity_splash)

        setAnimation()
        setSplash()
    }
    // SplashActivity Setting
    fun setSplash(){
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()}, SPLASH_TIME)
    }

    // TextView Animation Setting
    fun setAnimation(){
        val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.alpha)
        binding.everyTextView.startAnimation(animation)
    }
}
