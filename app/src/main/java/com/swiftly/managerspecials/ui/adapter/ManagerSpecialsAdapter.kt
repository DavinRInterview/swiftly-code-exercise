package com.swiftly.managerspecials.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swiftly.managerspecials.ui.model.ManagerSpecialsRowItem

class ManagerSpecialsAdapter(private val data: List<ManagerSpecialsRowItem>) :
    RecyclerView.Adapter<ManagerSpecialsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManagerSpecialsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ManagerSpecialsViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ManagerSpecialsViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}