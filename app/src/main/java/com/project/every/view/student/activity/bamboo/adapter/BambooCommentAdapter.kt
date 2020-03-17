package com.project.every.view.student.activity.bamboo.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.every.DTO.student.bamboo.BambooReplyList
import com.project.every.R
import com.project.every.base.StudentData
import com.project.every.view.student.activity.bamboo.BambooMoreActivity
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

        holder.apply {
            bind(mContext, item)
            itemView.tag = item
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(context : Context, item : BambooReplyList){
            itemView.comment_content.text = item.content

            // studentName
            getStudentInfo(item.student_idx, itemView.comment_studentName)

            // timer
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val date = format.parse(item.created_at)
            itemView.comment_timer.text = formatTimeString(date)

            // more
            if(StudentData.studentIdx.value == item.student_idx) itemView.more.visibility = View.VISIBLE
            else itemView.more.visibility = View.GONE

            itemView.more.setOnClickListener(View.OnClickListener {
                val intent = Intent(context, BambooMoreActivity::class.java)
                intent.putExtra("idx", item.idx)
                intent.putExtra("content", item.content)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            })

            itemView.comment_layout.setOnLongClickListener {
                val intent = Intent(context, BambooMoreActivity::class.java)
                intent.putExtra("idx", item.idx)
                intent.putExtra("content", item.content)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
                true
            }
        }
    }
}