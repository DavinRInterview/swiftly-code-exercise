/*
MIT License

Copyright (c) 2020 DavinRInterview

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

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
import kotlin.math.min

open class ManagerSpecialsViewHolder(private val inflater: LayoutInflater, private val parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.manager_special_row, parent, false)) {

    private var row: LinearLayout? = null

    init {
        row = itemView.findViewById(R.id.manager_special_row_contents)
    }

    @VisibleForTesting
    fun bind(rowData: ManagerSpecialsRowItem) {

        if (row != null && !row!!.isEmpty()) {
            return
        }
        for (item in rowData.managerSpecialsItemList) {
            val newView = inflater.inflate(R.layout.manager_special_item, row, false)
            newView.findViewById<TextView>(R.id.product_name).text = item.displayName
            newView.findViewById<TextView>(R.id.original_price_text).apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                text = item.originalPrice
            }
            newView.findViewById<TextView>(R.id.price_text).text = item.price
            val augment = (25 * (rowData.managerSpecialsItemList.size + 1)) / rowData.managerSpecialsItemList.size
            val paddingUnit = newView.paddingBottom
            val newWidth = ((item.width.toFloat() /rowData.canvasUnit) * parent.measuredWidth).toInt() - augment
            val newHeight = (((item.height.toFloat() /rowData.canvasUnit) * parent.measuredWidth) - (paddingUnit * 2.5)).toInt()
            val smallerDimension = min((newWidth * 0.45).toInt(), (newHeight * 0.45).toInt())
            val heightDimension = (newHeight * 0.4).toInt()
            val smallerRatio = min((item.width.toFloat() /rowData.canvasUnit), (item.height.toFloat() /rowData.canvasUnit))
            newView.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                width = newWidth
                height = newHeight
                //TODO: This is a hack. Figure out why the set margins aren't rendering right
                leftMargin = paddingUnit * 2
                bottomMargin = (paddingUnit * 1.5).toInt()
                topMargin = (paddingUnit * .75).toInt()
                if (item == rowData.managerSpecialsItemList.last()) {
                    rightMargin = paddingUnit * 2
                }
            }
            newView.setPadding(
                (newView.paddingLeft * smallerRatio).toInt(),
                (newView.paddingTop * smallerRatio).toInt(),
                (newView.paddingRight * smallerRatio).toInt(),
                (newView.paddingBottom * smallerRatio).toInt()
            )
            scaleInternalViews(newView, smallerRatio, smallerDimension, heightDimension)
            Glide.with(parent.context)
                .load(item.imageUrl)
                .into(newView.findViewById(R.id.product_image))
            row?.addView(newView)
        }
    }

    @VisibleForTesting
    fun scaleInternalViews(view : View, ratio: Float, widthDimension: Int, heightDimension: Int) {
        view.findViewById<ImageView>(R.id.product_image).updateLayoutParams<ViewGroup.MarginLayoutParams> {
            width = min(widthDimension, (width * ratio).toInt())
            height = min(widthDimension, (height * ratio).toInt())
            leftMargin = (leftMargin * ratio).toInt()
            topMargin = (topMargin * ratio).toInt()
            bottomMargin = (bottomMargin * ratio).toInt()
        }
        val nameView = view.findViewById<TextView>(R.id.product_name)
        nameView.textSize = nameView.textSize * ratio
        nameView.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            height = heightDimension
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