package com.project.every.view.student.fragment.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.every.R
import kotlin.collections.ArrayList

class MealsAdapter(val meals : ArrayList<String>) : RecyclerView.Adapter<MealsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.meals_item, parent, false)
        return ViewHolder(
            inflater
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewHolder = holder
        viewHolder.meals_textView.text = meals.get(position)
    }

    override fun getItemCount(): Int = meals.size

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var meals_textView : TextView = itemView.findViewById(R.id.meals_textView)
    }
}