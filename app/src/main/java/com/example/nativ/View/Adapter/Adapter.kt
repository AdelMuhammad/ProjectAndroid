package com.example.nativ.View.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nativ.Model.Dimen.ItemModel
import com.example.nativ.R

class Adapter(val context: Context, val list: ArrayList<ItemModel>): RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Adapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.navigation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        val item = list[position]
        // Bind your data to the views in your ViewHolder here
        // For example: holder.textView.text = item.text
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class ViewHolder (itemModel: View): RecyclerView.ViewHolder(itemModel) {

    }

}

