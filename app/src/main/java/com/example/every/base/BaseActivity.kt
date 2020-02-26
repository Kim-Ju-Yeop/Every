package com.example.every.base

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.every.R
import com.example.every.network.request.model.signup.SignUpDataStudent
import com.example.every.network.request.model.signup.SignUpDataWorker

object SignUpData{
    val signUpDataStudent = SignUpDataStudent()
    val signUpDataWorker = SignUpDataWorker()

    var identityData : Int? = null
}

abstract class BaseActivity : AppCompatActivity() {

    abstract fun observerViewModel()

    // Toolbar Setting
    fun toolbarInit(toolbar : Toolbar){
        setSupportActionBar(toolbar)
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
}