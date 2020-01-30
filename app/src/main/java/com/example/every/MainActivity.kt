package com.example.every

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.every.databinding.ActivityMainBinding
import com.example.every.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
