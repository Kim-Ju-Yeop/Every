package com.example.every.activity.signup.signup.data

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.databinding.ActivitySchoolBinding
import com.example.every.viewmodel.signup.signup.data.SchoolViewModel

class SchoolActivity : AppCompatActivity() {

    lateinit var binding : ActivitySchoolBinding
    lateinit var viewModel : SchoolViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school)

        binding = DataBindingUtil.setContentView(this@SchoolActivity, R.layout.activity_school)
        viewModel = ViewModelProviders.of(this@SchoolActivity).get(SchoolViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SchoolActivity

        observerViewModel()
    }

    fun observerViewModel(){
        with(viewModel){
            onSearchechEvent.observe(this@SchoolActivity, Observer {
                startActivity(Intent(this@SchoolActivity, SchoolListActivity::class.java))
            })
        }
    }
}
