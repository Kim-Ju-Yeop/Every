package com.project.every.view.student.fragment.schedule.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.every.R
import com.project.every.view.student.activity.schedule.SchedulePostActivity

class NoScheduleAdapter(val mContext : Context) : RecyclerView.Adapter<NoScheduleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater : LayoutInflater = LayoutInflater.from(parent.context)
        var itemView = inflater.inflate(R.layout.no_schedule_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listener = View.OnClickListener {
            mContext.startActivity(Intent(mContext, SchedulePostActivity::class.java))
        }
        holder.apply {
            bind(listener)
        }
    }

    override fun getItemCount(): Int = 1

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view: View = itemView
        fun bind(listener: View.OnClickListener) = view.setOnClickListener(listener)
    }
}