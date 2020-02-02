package com.example.every.activity.signup.signup.data

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.activity.base.BaseActivity
import com.example.every.activity.signup.SignUpData
import com.example.every.databinding.ActivityWorkerBinding
import com.example.every.viewmodel.signup.signup.data.WorkerViewModel

class WorkerActivity : BaseActivity() {

    lateinit var binding : ActivityWorkerBinding
    lateinit var viewModel : WorkerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker)

        binding = DataBindingUtil.setContentView(this@WorkerActivity, R.layout.activity_worker)
        viewModel = ViewModelProviders.of(this@WorkerActivity).get(WorkerViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@WorkerActivity

        toolbarInit(binding.toolbar)
        workerNameCheck()
        setSpinner()
        observerViewModel()
    }

    fun workerNameCheck(){
        binding.workerNameEditText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(viewModel.checkType(binding.workerNameEditText.text.toString())){
                    binding.nextButton.setBackgroundResource(R.drawable.background_corners_gradient)
                    binding.nextButton.isEnabled = true
                }else{
                    binding.nextButton.setBackgroundResource(R.color.gray)
                    binding.nextButton.isEnabled = false
                }
            }
        })
    }

    fun setSpinner(){
        val items = resources.getStringArray(R.array.workerData)
        val adapter = ArrayAdapter(this@WorkerActivity, android.R.layout.simple_spinner_dropdown_item, items)
        binding.spinner.adapter = adapter
    }

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@WorkerActivity, Observer {
                SignUpData.signUpDataWorker.work_place = viewModel.workerName.value.toString()
                SignUpData.signUpDataWorker.work_category = binding.spinner.selectedItemPosition.plus(1)

                startActivity(Intent(this@WorkerActivity, SignUpFinishActivity::class.java))
            })
        }
    }
}
