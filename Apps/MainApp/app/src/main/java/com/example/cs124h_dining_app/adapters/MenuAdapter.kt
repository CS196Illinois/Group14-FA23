package com.example.cs124h_dining_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cs124h_dining_app.R
import com.example.cs124h_dining_app.models.MenuItem

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    private var data: List<MenuItem> = listOf()

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var text: TextView = view.findViewById(R.id.foodName)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MenuAdapter.ViewHolder, position: Int) {
        holder.text.text = data[position].name
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun submitData(newList: List<MenuItem>) {
        data = newList
        notifyDataSetChanged()
    }
}