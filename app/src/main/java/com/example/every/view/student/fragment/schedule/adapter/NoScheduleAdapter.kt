package com.example.every.view.student.fragment.schedule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.every.DTO.student.schedule.SchedulesList
import com.example.every.R

class NoScheduleAdapter(val mContext : Context) : RecyclerView.Adapter<NoScheduleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater : LayoutInflater = LayoutInflater.from(parent.context)
        var itemView = inflater.inflate(R.layout.no_schedule_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listener = View.OnClickListener {
            // 일정 추가 이벤트 이동
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