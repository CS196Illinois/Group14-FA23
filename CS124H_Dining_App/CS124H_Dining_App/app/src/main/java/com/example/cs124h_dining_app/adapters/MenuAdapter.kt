package com.example.cs124h_dining_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.cs124h_dining_app.R
import com.example.cs124h_dining_app.models.MenuItem

class MenuAdapter : BaseExpandableListAdapter() {
    var cafeList = listOf<String>()
    var cafeMenus = mapOf<String, List<MenuItem>>()

    override fun getGroupCount(): Int {
        return cafeList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return cafeMenus[cafeList[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return cafeList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return cafeMenus[cafeList[groupPosition]]!![childPosition]!!
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(parent!!.context).inflate(R.layout.cafe_view, parent, false)
        }

        convertView!!.findViewById<TextView>(R.id.cafeName).text = cafeList[groupPosition]

        return convertView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(parent!!.context).inflate(R.layout.menu_view, parent, false)
        }

        convertView!!.findViewById<TextView>(R.id.foodName).text = cafeMenus[cafeList[groupPosition]]!![childPosition]!!.name

        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}