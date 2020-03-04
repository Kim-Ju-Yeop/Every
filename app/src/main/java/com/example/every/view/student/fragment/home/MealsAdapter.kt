package com.example.every.view.student.fragment.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.every.DTO.student.bamboo.BambooPostList
import com.example.every.R
import com.example.every.view.student.activity.bamboo.BambooCommentActivity
import com.example.every.base.StudentData
import kotlinx.android.synthetic.main.bamboo_item.view.*
import java.util.*
import kotlin.collections.ArrayList
import java.text.SimpleDateFormat

class MealsAdapter(val meals : ArrayList<String>) : RecyclerView.Adapter<MealsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.meals_item, parent, false)
        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewHolder = holder
        viewHolder.meals_textView.text = meals.get(position)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var meals_textView : TextView = itemView.findViewById(R.id.meals_textView)
    }
}