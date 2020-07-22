package com.swiftly.managerspecials.ui.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.core.view.isEmpty
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swiftly.managerspecials.R
import com.swiftly.managerspecials.ui.model.ManagerSpecialsRowItem

class ManagerSpecialsViewHolder(private val inflater: LayoutInflater, private val parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.manager_special_row, parent, false)) {

    private var row: LinearLayout? = null

    init {
        row = itemView.findViewById(R.id.manager_special_row_contents)
    }

    fun bind(rowData: ManagerSpecialsRowItem) {

        if (row != null && !row!!.isEmpty()) {
            return
        }
        if (rowData.managerSpecialsItemList.size == 1) {
            val item = rowData.managerSpecialsItemList[0]
            val newView = inflater.inflate(R.layout.manager_special_item, row, false)
            row?.addView(newView)
            newView.findViewById<TextView>(R.id.product_name).text = item.displayName
            newView.findViewById<TextView>(R.id.original_price_text).apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                text = item.originalPrice
            }
            newView.findViewById<TextView>(R.id.price_text).text = item.price
            val newWidth = ((item.width.toFloat() /rowData.canvasUnit) * parent.measuredWidth).toInt() - 50
            val newHeight = ((item.height.toFloat() /rowData.canvasUnit) * parent.measuredWidth).toInt() - 30
            val smallerDimension = (newWidth * 0.45).toInt()
            val smallerRatio = Math.min((item.width.toFloat() /rowData.canvasUnit), (item.height.toFloat() /rowData.canvasUnit))
            newView.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                width = newWidth
                height = newHeight
                bottomMargin = 20
                topMargin = 10
                leftMargin = 25
                rightMargin = 25
            }
            newView.setPadding(
                (newView.paddingLeft * smallerRatio).toInt(),
                (newView.paddingTop * smallerRatio).toInt(),
                (newView.paddingRight * smallerRatio).toInt(),
                (newView.paddingBottom * smallerRatio).toInt()
            )
            scaleInternalViews(newView, smallerRatio, smallerDimension)
            Glide.with(parent.context)
                .load(item.imageUrl)
                .into(newView.findViewById(R.id.product_image))
        }
        else {
            for (item in rowData.managerSpecialsItemList) {
                val newView = inflater.inflate(R.layout.manager_special_item, row, false)
                row?.addView(newView)
                newView.findViewById<TextView>(R.id.product_name).text = item.displayName
                newView.findViewById<TextView>(R.id.original_price_text).apply {
                    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    text = item.originalPrice
                }
                newView.findViewById<TextView>(R.id.price_text).text = item.price
                val augment = (25 * (rowData.managerSpecialsItemList.size + 1)) / rowData.managerSpecialsItemList.size
                val newWidth = ((item.width.toFloat() /rowData.canvasUnit) * parent.measuredWidth).toInt() - augment
                val newHeight = ((item.height.toFloat() /rowData.canvasUnit) * parent.measuredWidth).toInt() - 30
                val smallerDimension = (newWidth * 0.45).toInt()
                val smallerRatio = Math.min((item.width.toFloat() /rowData.canvasUnit), (item.height.toFloat() /rowData.canvasUnit))
                newView.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    width = newWidth
                    height = newHeight
                    leftMargin = 25
                    bottomMargin = 20
                    topMargin = 10
                    if (item == rowData.managerSpecialsItemList.last()) {
                        rightMargin = 25
                    }
                }
                newView.setPadding(
                    (newView.paddingLeft * smallerRatio).toInt(),
                    (newView.paddingTop * smallerRatio).toInt(),
                    (newView.paddingRight * smallerRatio).toInt(),
                    (newView.paddingBottom * smallerRatio).toInt()
                )
                scaleInternalViews(newView, smallerRatio, smallerDimension)
                Glide.with(parent.context)
                    .load(item.imageUrl)
                    .into(newView.findViewById(R.id.product_image))
            }
        }
    }

    @VisibleForTesting
    private fun scaleInternalViews(view : View, ratio: Float, dimension: Int) {
        view.findViewById<ImageView>(R.id.product_image).updateLayoutParams<ViewGroup.MarginLayoutParams> {
            width = Math.min(dimension, (width * ratio).toInt())
            height = Math.min(dimension, (height * ratio).toInt())
            leftMargin = (leftMargin * ratio).toInt()
            topMargin = (topMargin * ratio).toInt()
            bottomMargin = (bottomMargin * ratio).toInt()
        }
        val nameView = view.findViewById<TextView>(R.id.product_name)
        nameView.textSize = nameView.textSize * ratio
        nameView.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            leftMargin = (leftMargin * ratio).toInt()
            rightMargin = (rightMargin * ratio).toInt()
            bottomMargin = (bottomMargin * ratio).toInt()
        }
        val originalPriceView = view.findViewById<TextView>(R.id.original_price_text)
        originalPriceView.textSize = originalPriceView.textSize * ratio
        originalPriceView.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            rightMargin = (rightMargin * ratio).toInt()
            topMargin = (topMargin * ratio).toInt()
        }
        val priceView = view.findViewById<TextView>(R.id.price_text)
        priceView.textSize = priceView.textSize * ratio
        priceView.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            rightMargin = (rightMargin * ratio).toInt()
            bottomMargin = (bottomMargin * ratio).toInt()
        }
    }
}