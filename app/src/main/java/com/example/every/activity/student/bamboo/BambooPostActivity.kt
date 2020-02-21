package com.example.every.activity.student.bamboo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.activity.student.StudentMainActivity
import com.example.every.databinding.ActivityBambooPostBinding
import com.example.every.viewmodel.student.bamboo.BambooPostViewModel

class BambooPostActivity : AppCompatActivity() {

    lateinit var binding : ActivityBambooPostBinding
    lateinit var viewModel : BambooPostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bamboo_post)

        binding = DataBindingUtil.setContentView(this@BambooPostActivity, R.layout.activity_bamboo_post)
        viewModel = ViewModelProviders.of(this@BambooPostActivity).get(BambooPostViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@BambooPostActivity

        toolbarInit(binding.toolbar)
        observerViewModel()
    }

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

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@BambooPostActivity, Observer {
                Toast.makeText(applicationContext, "정상적으로 글 작성을 수행하였습니다.", Toast.LENGTH_SHORT).show()
                finish()
            })
            onFailEvent.observe(this@BambooPostActivity, Observer {
                Toast.makeText(applicationContext, "먼저 글을 작성해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
            })
        }
    }
}
