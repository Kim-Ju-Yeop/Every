package com.example.every.activity.signup.signup.data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.databinding.ActivityEmailPasswordBinding
import com.example.every.databinding.ActivityNameBirthBinding
import com.example.every.viewmodel.signup.signup.data.Email_PasswordViewModel
import com.example.every.viewmodel.signup.signup.data.Name_BirthViewModel

class Name_BirthActivity : AppCompatActivity() {

    lateinit var binding : ActivityNameBirthBinding
    lateinit var viewModel : Name_BirthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name__birth)

        binding = DataBindingUtil.setContentView(this@Name_BirthActivity, R.layout.activity_name__birth)
        viewModel = ViewModelProviders.of(this@Name_BirthActivity).get(Name_BirthViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@Name_BirthActivity
    }
}
