package com.example.every.fragment.bamboo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.every.DTO.student.BambooPostList
import com.example.every.R
import com.example.every.fragment.base.tokenData
import kotlinx.android.synthetic.main.bamboo_item.view.*
import java.util.*
import kotlin.collections.ArrayList
import java.text.SimpleDateFormat

class BambooAdapter(val mContext : Context, val items : ArrayList<BambooPostList>) : RecyclerView.Adapter<BambooAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater : LayoutInflater = LayoutInflater.from(parent.context)
        var itemView : View = inflater.inflate(R.layout.bamboo_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var item : BambooPostList = items.get(position)
        val listener = View.OnClickListener {

        }

        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var view : View = itemView

        fun bind(listener : View.OnClickListener, item : BambooPostList){
            itemView.idx.text = "#${item.idx}번째 이야기"
            itemView.content.text = item.content

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
            getBambooComment(tokenData.token.value.toString(), item.idx, itemView.comment)
        }
    }
}