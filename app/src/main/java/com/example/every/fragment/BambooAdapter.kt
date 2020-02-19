package com.example.every.fragment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.every.DTO.student.BambooPostList
import com.example.every.R
import kotlinx.android.synthetic.main.bamboo_item.view.*

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
            itemView.created_at.text = item.created_at
        }
    }
}