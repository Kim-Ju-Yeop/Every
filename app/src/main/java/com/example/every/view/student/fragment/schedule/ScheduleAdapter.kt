package com.example.every.view.student.fragment.schedule

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.every.DTO.signup.SchoolDataList
import com.example.every.DTO.student.schedule.SchedulesList
import com.example.every.R
import kotlinx.android.synthetic.main.schedule_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleAdapter(val mContext : Context, val items : ArrayList<SchedulesList>) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater : LayoutInflater = LayoutInflater.from(parent.context)
        var itemView = inflater.inflate(R.layout.schedule_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item : SchedulesList = items.get(position)

        val listener = View.OnClickListener {

        }

        holder.apply {
            bind(listener, item)
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var view : View = itemView

        fun bind(listener : View.OnClickListener, item : SchedulesList){

            // Title_TextView
            itemView.title_textView.setSingleLine(true)
            itemView.title_textView.ellipsize = TextUtils.TruncateAt.END
            itemView.title_textView.isSelected = true
            itemView.title_textView.text = item.title

            itemView.date_textView.text = "${item.start_date.substring(0, 4)}년 ${item.start_date.substring(5, 7)}월 ${item.start_date.substring(8, 10)}일 ~ " +
                                          "${item.end_date.substring(0, 4)}년 ${item.end_date.substring(5, 7)}월 ${item.end_date.subSequence(8, 10)}일"
            view.setOnClickListener(listener)
        }
    }
}