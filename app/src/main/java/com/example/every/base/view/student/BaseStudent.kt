package com.example.every.base.view.student

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData

open class BaseStudentFragment : Fragment()
open class BaseStudentActivity : AppCompatActivity()

object tokenData{
    val token = MutableLiveData<String>()
}
object idxData {
    val idx = MutableLiveData<Int>()
}

fun toastMessage(mContext : Context, message : String){
    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
}