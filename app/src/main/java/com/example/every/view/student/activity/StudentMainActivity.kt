package com.example.every.view.student.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.example.every.R
import com.example.every.databinding.ActivityStudentMainBinding
import com.example.every.view.student.fragment.bamboo.StudentBambooFragment
import com.example.every.view.student.fragment.StudentMainFragment
import com.example.every.view.student.fragment.StudentMoreFragment
import com.example.every.view.student.fragment.StudentScheduleFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class StudentMainActivity : AppCompatActivity() {

    lateinit var binding : ActivityStudentMainBinding

    val studentMainFragment =
        StudentMainFragment()
    val studentBambooFragment = StudentBambooFragment()
    val studentScheduleFragment =
        StudentScheduleFragment()
    val studentMoreFragment =
        StudentMoreFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_main)

        binding = DataBindingUtil.setContentView(this@StudentMainActivity, R.layout.activity_student_main)
        binding.lifecycleOwner = this@StudentMainActivity

        init()
        selectedEvent()
    }

    private fun init(){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, studentMainFragment).commitAllowingStateLoss()
    }

    private fun selectedEvent(){
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                when(item.itemId){
                    R.id.home -> {
                        fragmentTransaction.replace(R.id.frameLayout, studentMainFragment).commitAllowingStateLoss()
                        return true
                    }
                    R.id.bamboo -> {
                        fragmentTransaction.replace(R.id.frameLayout, studentBambooFragment).commitAllowingStateLoss()
                        return true
                    }
                    R.id.schedule -> {
                        fragmentTransaction.replace(R.id.frameLayout, studentScheduleFragment).commitAllowingStateLoss()
                        return true
                    }
                    R.id.more -> {
                        fragmentTransaction.replace(R.id.frameLayout, studentMoreFragment).commitAllowingStateLoss()
                        return true
                    }
                }
                return false
            }
        })
    }
}
