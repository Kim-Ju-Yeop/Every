package com.example.every.view.activity.student.bamboo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.every.DTO.student.BambooReplyList
import com.example.every.R
import kotlinx.android.synthetic.main.bamboo_comment_item.view.*
import java.text.SimpleDateFormat

class BambooCommentAdapter(val mContext : Context, val items : ArrayList<BambooReplyList>) : RecyclerView.Adapter<BambooCommentAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater : LayoutInflater = LayoutInflater.from(parent.context)
        var itemView : View = inflater.inflate(R.layout.bamboo_comment_item, parent, false)

        return ViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item : BambooReplyList = items.get(position)
        val listenr = View.OnClickListener {

        }

        holder.apply {
            bind(listenr, item)
            itemView.tag = item
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var view : View = itemView
        fun bind(listener : View.OnClickListener, item : BambooReplyList){
            itemView.comment_content.text = item.content
            view.setOnClickListener(listener)

            // studentName
            getStudentInfo(item.student_idx, itemView.comment_studentName)

            // timer
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val date = format.parse(item.created_at)
            itemView.comment_timer.text = formatTimeString(date)
        }
    }
}