package com.example.every.view.signup.signup.data

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.every.DTO.signup.SchoolDataList
import com.example.every.R
import kotlinx.android.synthetic.main.school_item.view.*

class SchoolAdapter(val mContext : Context, val items : ArrayList<SchoolDataList>) : RecyclerView.Adapter<SchoolAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater : LayoutInflater = LayoutInflater.from(parent.context)
        var itemView : View = inflater.inflate(R.layout.school_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item : SchoolDataList = items.get(position)
        val listener = View.OnClickListener {
            val sharedPreferences = mContext.getSharedPreferences("checkSchool", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.putString("school_name", item.school_name)
            editor.putString("school_id", item.school_id)
            editor.commit()

            (mContext as Activity).finish()
        }

        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var view : View = itemView

        fun bind(listener: View.OnClickListener, item: SchoolDataList){
            itemView.schoolName.text = item.school_name

            if(item.school_location == null) itemView.schoolLocate.text = "위치 정보를 불러올 수 없습니다."
            else itemView.schoolLocate.text = item.school_location

            itemView.schoolName.isSelected = true
            itemView.schoolLocate.isSelected = true

            view.setOnClickListener(listener)
        }
    }
}