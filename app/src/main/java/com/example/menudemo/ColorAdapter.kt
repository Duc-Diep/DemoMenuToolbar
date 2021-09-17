package com.example.menudemo

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ColorAdapter(var listColor:ArrayList<String>, context: Context):ArrayAdapter<String>(context,R.layout.color_item,listColor) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var inflater = (context as Activity).layoutInflater
        var view = inflater.inflate(R.layout.color_item,parent,false)
        var tvName = view.findViewById<TextView>(R.id.tv_name)
        tvName.text = listColor[position]
        return view
    }

    fun removeItem(index: Int) {
        listColor.removeAt(index)
        notifyDataSetChanged()
    }
}