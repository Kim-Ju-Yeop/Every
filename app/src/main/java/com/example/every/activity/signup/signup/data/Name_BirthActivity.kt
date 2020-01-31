package com.example.every.activity.signup.signup.data

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.activity.signup.SignUpData
import com.example.every.databinding.ActivityNameBirthBinding
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

        FocusEditText()
        observerViewModel()
    }

    fun FocusEditText(){
        binding.nameEditText.setOnFocusChangeListener(object : View.OnFocusChangeListener{
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if(!hasFocus){
                    if(!viewModel.checkEmpty(binding.nameEditText.text.toString(), 0)){
                        binding.nameAnswer.visibility = View.GONE
                    } else{
                        binding.nameAnswer.visibility = View.VISIBLE
                    }
                }
            }
        })
        binding.birthEditText.setOnFocusChangeListener(object : View.OnFocusChangeListener{
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if(!hasFocus){
                    if(!viewModel.checkEmpty(binding.birthEditText.text.toString(), 1)){
                        binding.birthAnswer.visibility = View.GONE
                    }else{
                        if(!viewModel.checkType(binding.birthEditText.text.toString())){
                            binding.birthAnswer.visibility = View.GONE
                        } else{
                            binding.birthAnswer.visibility = View.VISIBLE
                        }
                    }
                }
            }
        })
    }

    fun observerViewModel(){
        with(viewModel){
            onSuccessEvent.observe(this@Name_BirthActivity, Observer {
                val checkIdentity = getSharedPreferences("checkIdentity", Context.MODE_PRIVATE)

                if(checkIdentity.getInt("identityData", 99) == 0){
                    SignUpData.signUpDataStudent.name = viewModel.name.value.toString()
                    SignUpData.signUpDataStudent.birth_year = Integer.parseInt(viewModel.birth.value.toString())
                } else if(checkIdentity.getInt("identityData", 99) == 1) {
                    SignUpData.signUpDataWorker.name = viewModel.name.value.toString()
                    SignUpData.signUpDataWorker.birth_year = Integer.parseInt(viewModel.birth.value.toString())
                }
//                startActivity(Intent(this@Name_BirthActivity, Name_BirthActivity::class.java))
                Toast.makeText(applicationContext, "띵동 성공하였습니다!", Toast.LENGTH_SHORT).show()
            })
            onFailEvent.observe(this@Name_BirthActivity, Observer {
                binding.nameAnswer.visibility = View.GONE
                binding.birthAnswer.visibility = View.GONE
                Toast.makeText(applicationContext, "이름과 태어난 년도를 다시 한 번 확인해주세요.", Toast.LENGTH_SHORT).show()
            })
        }
    }
}
