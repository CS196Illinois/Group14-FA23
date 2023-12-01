package com.example.cs124h_dining_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListView
import com.example.cs124h_dining_app.R
import com.example.cs124h_dining_app.adapters.MenuAdapter
import com.example.cs124h_dining_app.data.source.DiningMenuDataSource
import com.example.cs124h_dining_app.data.DiningMenuRepository
import com.example.cs124h_dining_app.models.MenuRequest

class MainActivity : AppCompatActivity() {
    private val adapter = MenuAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val menuRepo = DiningMenuRepository
        menuRepo.getData(MenuRequest("3", "2023-11-28")) {
            val data = it["Dinner"] ?: throw Exception("Failed to fetch menu data")
            adapter.cafeList = data.keys.toList()
            adapter.cafeMenus = data
            val menu: ExpandableListView = findViewById(R.id.menuInfo)
            runOnUiThread {
                menu.setAdapter(adapter)
            }
        }

    }
}