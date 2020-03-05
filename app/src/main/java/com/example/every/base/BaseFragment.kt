package com.example.every.base

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView

object StudentData{
    val token = MutableLiveData<String>()
    val postIdx = MutableLiveData<Int>()
    val studentIdx = MutableLiveData<Int>()
}

abstract class BaseFragment : Fragment(){

    abstract fun observerViewModel()

    fun toastMessage(context : Context, message : String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun setVisible(questionLayout : LinearLayout, recyclerView: RecyclerView, who : Int){
        if(who == 0){
            questionLayout.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else{
            questionLayout.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }
}