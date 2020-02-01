package com.example.every.activity.signup.signup.data

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.activity.signup.SignUpData
import com.example.every.databinding.ActivityPhoneBinding
import com.example.every.viewmodel.signup.signup.data.PhoneViewModel
import kotlinx.android.synthetic.main.activity_phone.*

class PhoneActivity : AppCompatActivity() {

    lateinit var binding : ActivityPhoneBinding
    lateinit var viewModel : PhoneViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)

        binding = DataBindingUtil.setContentView(this@PhoneActivity, R.layout.activity_phone)
        viewModel = ViewModelProviders.of(this@PhoneActivity).get(PhoneViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@PhoneActivity

        binding.phoneEditText.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        toolbarInit()
        FocusEditText()
        observerViewModel()
    }
    fun toolbarInit(){
        setSupportActionBar(binding.toolbar)
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
    fun FocusEditText() {
        binding.phoneEditText.setOnFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if (!hasFocus) {
                    if (viewModel.checkEmpty(binding.phoneEditText.text.toString())) {
                        if (viewModel.checkType(binding.phoneEditText.text.toString())) {
                        }
                    }
                }
            }
        })
    }
    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@PhoneActivity, Observer {
                val checkIdentity = getSharedPreferences("checkIdentity", Context.MODE_PRIVATE)

                if(checkIdentity.getInt("identityData", 99) == 0){
                    SignUpData.signUpDataStudent.phone = viewModel.phone.value.toString()
                } else if(checkIdentity.getInt("identityData", 99) == 1) {
                    SignUpData.signUpDataWorker.phone = viewModel.phone.value.toString()
                }
                startActivity(Intent(this@PhoneActivity, SchoolActivity::class.java))
            })
            onFailEvent.observe(this@PhoneActivity, Observer {
                Toast.makeText(applicationContext, "전화번호를 다시 한 번 확인해주세요.", Toast.LENGTH_SHORT).show()
            })
        }
    }
}
