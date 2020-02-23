package com.example.every.activity.student.bamboo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.every.DTO.student.BambooReplyList
import com.example.every.R
import kotlinx.android.synthetic.main.activity_bamboo_comment.view.*

class BambooCommentAdapter(val mContext : Context, val items : ArrayList<BambooReplyList>) : RecyclerView.Adapter<BambooCommentAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater : LayoutInflater = LayoutInflater.from(parent.context)
        var itemView : View = inflater.inflate(R.layout.bamboo_comment_item, parent, false)

        return ViewHolder(itemView)
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
            view.setOnClickListener(listener)

            itemView.content.text = item.content
        }
    }
}