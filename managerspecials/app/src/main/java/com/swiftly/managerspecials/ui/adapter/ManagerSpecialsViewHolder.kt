package com.swiftly.managerspecials.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swiftly.managerspecials.R
import com.swiftly.managerspecials.ui.model.ManagerSpecialsRowItem

class ManagerSpecialsViewHolder(inflater: LayoutInflater, private val parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.manager_special_row, parent, false)) {

    private var row: ConstraintLayout? = null

    init {
        row = itemView.findViewById(R.id.manager_special_row_contents)
    }

    fun bind(item: ManagerSpecialsRowItem) {
        Glide.with(parent.context)
    }
}