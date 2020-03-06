package com.example.every.view.signup.signup.data

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.base.BaseActivity
import com.example.every.base.SignUpData
import com.example.every.databinding.ActivityWorkerBinding
import com.example.every.viewmodel.signup.signup.data.WorkerSignUpViewModel

class WorkerSignUpActivity : BaseActivity() {

    lateinit var binding : ActivityWorkerBinding
    lateinit var viewModel : WorkerSignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@WorkerSignUpActivity, R.layout.activity_worker)
        viewModel = ViewModelProviders.of(this@WorkerSignUpActivity).get(WorkerSignUpViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@WorkerSignUpActivity

        setSpinner()
        workerNameCheck()
        toolbarInit(binding.toolbar)
    }

    fun setSpinner(){
        val items = resources.getStringArray(R.array.workerData)
        val adapter = ArrayAdapter(this@WorkerSignUpActivity, android.R.layout.simple_spinner_dropdown_item, items)
        binding.spinner.adapter = adapter
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

    override fun observerViewModel(){
        with(viewModel){
            onNextEvent.observe(this@WorkerSignUpActivity, Observer {
                SignUpData.signUpDataWorker.work_place = viewModel.workerName.value.toString()
                SignUpData.signUpDataWorker.work_category = binding.spinner.selectedItemPosition.plus(1)
                startActivity(Intent(this@WorkerSignUpActivity, SignUpFinishActivity::class.java))
            })
        }
    }
}
