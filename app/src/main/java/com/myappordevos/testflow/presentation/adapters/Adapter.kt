package com.myappordevos.testflow.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.myappordevos.testflow.R
import com.myappordevos.testflow.databinding.ItemResultBinding

class Adapter : RecyclerView.Adapter<Adapter.MyViewHolder>() {
    var myListItem : List<Competition> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        private val bindingItem = ItemResultBinding.bind(itemView)
        fun inflateItem(item:Competition) {
            bindingItem.root.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_result,parent,false))

    override fun getItemCount() = myListItem.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.inflateItem(myListItem[position])
    }
}