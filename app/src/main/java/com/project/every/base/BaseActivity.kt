package com.project.every.base

import android.content.Context
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.project.every.R
import com.project.every.network.request.model.signup.SignUpDataStudent
import com.project.every.network.request.model.signup.SignUpDataWorker

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

    // ToastMessage
    fun toastMessage(context : Context, message : String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    // Visible
    fun setVisible(questionLayout : LinearLayout, recyclerView: RecyclerView, who : Int){
        if(who == 0){
            questionLayout.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else{
            questionLayout.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun onResume() {
        super.onResume()
        observerViewModel()
    }
}