package com.websarva.wings.android.busschedulemaker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class SpinnerAdapter(context: Context,spinnerItem: Int, ids:MutableList<String>,titles:MutableList<String>) : BaseAdapter() {
    val ids = ids
    val titles = titles
    private val inflater = LayoutInflater.from(context)
    override fun getCount(): Int {
        return ids.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: inflater.inflate(R.layout.spinner_item, parent, false)
        val tvIdItem = view.findViewById<TextView>(R.id.tvIdItem)
        val tvTitleItem =view.findViewById<TextView>(R.id.tvTitleItem)

        var tvIdItems:MutableList<String> = mutableListOf()
        var tvTitleItems:MutableList<String> = mutableListOf()
        for(i in 0 until ids.size){
            tvIdItems.add(ids[i])
            tvTitleItems.add(titles[i])
        }
        tvIdItem.setText(tvIdItems[position])
        tvTitleItem.setText(tvTitleItems[position])

        return view
    }
}