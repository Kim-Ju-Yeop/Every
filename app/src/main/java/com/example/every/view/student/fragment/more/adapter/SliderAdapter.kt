package com.example.every.view.student.fragment.more.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.every.R
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderAdapter(val mContext : Context, val mCount : Int) : SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapterViewHolder {
        val inflate = LayoutInflater.from(parent!!.context).inflate(R.layout.slider_item, null)
        return SliderAdapterViewHolder(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterViewHolder?, position: Int) {
        when(position){
            0 -> settingImage(viewHolder!!.view, R.drawable.juyeop, viewHolder.imageView)
            1 -> settingImage(viewHolder!!.view, R.drawable.ho, viewHolder.imageView)
            2 -> settingImage(viewHolder!!.view, R.drawable.hoon, viewHolder.imageView)
            3 -> settingImage(viewHolder!!.view, R.drawable.jinu, viewHolder.imageView)
            4 -> settingImage(viewHolder!!.view, R.drawable.min, viewHolder.imageView)
        }
    }

    fun settingImage(view: View, image: Int, imageView: ImageView){
        Glide.with(view)
            .load(image)
            .fitCenter()
            .into(imageView)
    }

    override fun getCount(): Int = mCount

    class SliderAdapterViewHolder(itemView : View) : SliderViewAdapter.ViewHolder(itemView) {
        val view : View = itemView
        val imageView : ImageView = itemView.findViewById(R.id.imageView)
    }
}