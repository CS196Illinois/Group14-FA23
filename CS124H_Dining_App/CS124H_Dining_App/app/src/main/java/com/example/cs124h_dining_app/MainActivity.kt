package com.example.cs124h_dining_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.TextView
import androidx.appcompat.view.menu.ExpandedMenuView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.cs124h_dining_app.adapters.MenuAdapter
import com.example.cs124h_dining_app.data.source.DiningMenuDataSource
import com.example.cs124h_dining_app.data.DiningMenuRepository
import com.example.cs124h_dining_app.models.MenuRequest
import com.example.cs124h_dining_app.models.MenuItem

/*
class MenuAdapter : ListAdapter<MenuItem, MenuAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cafe_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MenuAdapter.ItemViewHolder, position: Int) {
        holder.textView.text = this.currentList[position].name
    }

    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.cafeName)
    }

    class DiffCallback : DiffUtil.ItemCallback<MenuItem>() {
        override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem == newItem
        }
    }
}
*/

class MainActivity : AppCompatActivity() {
    private val adapter = MenuAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val menu: ExpandableListView = findViewById(R.id.menuInfo)
        val menuRepo = DiningMenuRepository(DiningMenuDataSource)
        val data = menuRepo.getData(MenuRequest("3", "2023-11-27"))["Dinner"]
            ?: throw Exception("Failed to fetch menu data")
        adapter.cafeList = data.keys.toList()
        adapter.cafeMenus = data
        menu.setAdapter(adapter)
    }
}