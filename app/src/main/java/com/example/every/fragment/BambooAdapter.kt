package com.example.every.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.every.DTO.student.BambooPostList
import com.example.every.R
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
            itemView.created_at.text = "${calendar.get(Calendar.YEAR)}년 ${calendar.get(Calendar.MONTH) + 1}월 ${calendar.get(Calendar.DAY_OF_MONTH)}일 ${getWeek(intDayNum)}"

            // timer TextView
            val format2 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val date2 = format2.parse(item.created_at)
            itemView.timer.text = formatTimeString(date2)
        }
    }
}

object TIME_MAXIMUM{
    var SEC = 60
    var MIN = 60
    var HOUR = 24
    var DAY = 30
    var MONTH = 12
}

fun formatTimeString(tempDate : Date) : String{
    val curTime : Long = System.currentTimeMillis()
    val regTime : Long = tempDate.time
    var diffTime : Long = (curTime - regTime) / 1000

    var msg : String? = null

    if(diffTime < TIME_MAXIMUM.SEC){
        msg = "방금 전"
        return msg!!
    }
    if((diffTime / TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN){
        diffTime /= TIME_MAXIMUM.SEC
        msg = "${diffTime}분 전"
        return msg!!
    } else diffTime /= TIME_MAXIMUM.SEC
    if ((diffTime / TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR){
        diffTime /= TIME_MAXIMUM.MIN
        msg = "${diffTime}시간 전"
        return msg!!
    } else diffTime /= TIME_MAXIMUM.MIN
    if((diffTime / TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY){
        diffTime /= TIME_MAXIMUM.HOUR
        msg = "${diffTime}일 전"
        return msg!!
    } else diffTime /= TIME_MAXIMUM.HOUR
    if((diffTime / TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH){
        diffTime /= TIME_MAXIMUM.DAY
        msg = "${diffTime}달 전"
        return msg!!
    }
    else {
        diffTime /= TIME_MAXIMUM.DAY
        msg = "${diffTime}년 전"
        return msg!!
    }
}

fun getWeek(dayNum : Int) : String{
    var day : String? = null

    when (dayNum) {
        1 -> day = "일요일"
        2 -> day = "월요일"
        3 -> day = "화요일"
        4 -> day = "수요일"
        5 -> day = "목요일"
        6 -> day = "금요일"
        7 -> day = "토요일"
    }
    return day.toString()
}