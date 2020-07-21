package com.swiftly.managerspecials.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swiftly.managerspecials.ui.model.ManagerSpecialsRowItem

class ManagerSpecialsAdapter(private val data: List<ManagerSpecialsRowItem>) :
    RecyclerView.Adapter<ManagerSpecialsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManagerSpecialsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ManagerSpecialsViewHolder(inflater, parent)
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ManagerSpecialsViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }
}