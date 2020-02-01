package com.example.every.activity.signup.signup.data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.activity.base.BaseActivity
import com.example.every.databinding.ActivitySchoolListBinding
import com.example.every.viewmodel.signup.signup.data.SchoolListViewModel

class SchoolListActivity : BaseActivity() {

    lateinit var binding : ActivitySchoolListBinding
    lateinit var viewModel : SchoolListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_school_list)

        binding = DataBindingUtil.setContentView(this@SchoolListActivity, R.layout.activity_school_list)
        viewModel = ViewModelProviders.of(this@SchoolListActivity).get(SchoolListViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SchoolListActivity

        observerViewModel()
    }

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@SchoolListActivity, Observer {
                val adapter = SchoolAdapter(this@SchoolListActivity, viewModel.schoolDataList)
                binding.recyclerView.adapter = adapter
            })
        }
    }
}
