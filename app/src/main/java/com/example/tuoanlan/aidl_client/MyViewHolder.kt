package com.example.tuoanlan.aidl_client

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.item_recycler.view.*

class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
    val textView:TextView =view.tv_name


}