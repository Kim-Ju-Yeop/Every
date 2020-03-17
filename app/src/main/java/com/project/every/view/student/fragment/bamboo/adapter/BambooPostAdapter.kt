package com.project.every.view.student.fragment.bamboo.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.every.DTO.student.bamboo.BambooPostList
import com.project.every.R
import com.project.every.view.student.activity.bamboo.BambooCommentActivity
import com.project.every.base.StudentData
import kotlinx.android.synthetic.main.bamboo_item.view.*
import java.util.*
import kotlin.collections.ArrayList
import java.text.SimpleDateFormat

class BambooPostAdapter(val mContext : Context, val items : ArrayList<BambooPostList>) : RecyclerView.Adapter<BambooPostAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater : LayoutInflater = LayoutInflater.from(parent.context)
        var itemView : View = inflater.inflate(R.layout.bamboo_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item : BambooPostList = items.get(position)
        val listener = View.OnClickListener {
            val intent = Intent(mContext, BambooCommentActivity::class.java)

            intent.putExtra("idx", item.idx)
            intent.putExtra("content", holder.itemView.content.text.toString())
            intent.putExtra("created_at", holder.itemView.created_at.text.toString())
            intent.putExtra("timer", holder.itemView.timer.text.toString())
            intent.putExtra("comment", holder.itemView.comment.text.toString())
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            mContext.startActivity(intent)
        }

        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var view : View = itemView

        fun bind(listener : View.OnClickListener, item : BambooPostList){
            itemView.idx.text = "#${item.idx}번째 이야기"
            itemView.content.text = item.content
            itemView.content.isSelected = true

            // created_at TextView
            val format = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
            val date = format.parse(item.created_at)

            val calendar = Calendar.getInstance()
            calendar.time = date

            val intDayNum = calendar.get(Calendar.DAY_OF_WEEK)
            itemView.created_at.text = "${calendar.get(Calendar.YEAR)}년 " +
                                       "${calendar.get(Calendar.MONTH) + 1}월 " +
                                       "${calendar.get(Calendar.DAY_OF_MONTH)}일 " +
                                       "${getWeek(intDayNum)}"

            // timer TextView
            val format2 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val date2 = format2.parse(item.created_at)
            itemView.timer.text = formatTimeString(date2)

            // comment TextView
            getBambooComment(StudentData.token.value.toString(), item.idx, itemView.comment)
            view.setOnClickListener(listener)
        }
    }
}