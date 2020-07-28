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

package com.swiftly.managerspecials.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView
import com.swiftly.managerspecials.service.ManagerSpecialsRepository
import com.swiftly.managerspecials.service.model.ManagerSpecialsItem
import com.swiftly.managerspecials.ui.adapter.ManagerSpecialsAdapter
import com.swiftly.managerspecials.ui.adapter.ManagerSpecialsViewHolder
import com.swiftly.managerspecials.ui.model.ManagerSpecialsRowItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ManagerSpecialsViewModelImpl(private val repository: ManagerSpecialsRepository) : ManagerSpecialsViewModel {

    private val loading = ObservableBoolean(true)
    private val showError = ObservableBoolean(false)
    private val specialsList = ObservableArrayList<ManagerSpecialsRowItem>()
    private val adapter: RecyclerView.Adapter<ManagerSpecialsViewHolder>

    init {
        adapter = ManagerSpecialsAdapter()
        adapter.setHasStableIds(true)
    }

    override fun updateSpecialsData() {
        loading.set(true)
        if (!specialsList.isEmpty()) {
            specialsList.clear()
        }
        repository.getManagerSpecials()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({
                loading.set(false)
                specialsList.addAll(groupItems(it.canvasUnit, dataValidation(it.canvasUnit, it.managerSpecials)))
            }, {
                loading.set(false)
                showError.set(true)
            })
    }

    @VisibleForTesting
    fun dataValidation(width: Int, items: List<ManagerSpecialsItem>) : List<ManagerSpecialsItem> {
        val newItems = ArrayList<ManagerSpecialsItem>()
        for (item in items) {
            if (item.height > 0 && item.width > 0 && item.width <= width) {
                newItems.add(item)
            }
        }
        return newItems
    }

    @VisibleForTesting
    fun groupItems(width: Int, items: List<ManagerSpecialsItem>) : List<ManagerSpecialsRowItem> {
        val rowItems = ArrayList<ManagerSpecialsRowItem>()
        if (items.isEmpty()) {
            return rowItems
        }
        var cumulativeWidth = 0;
        var newItems = ArrayList<ManagerSpecialsItem>()
        for (item in items) {
            cumulativeWidth += item.width
            if (cumulativeWidth > width) {
                rowItems.add(ManagerSpecialsRowItem(width, newItems))
                cumulativeWidth = item.width;
                newItems = ArrayList()
            }
            newItems.add(item)
        }
        rowItems.add(ManagerSpecialsRowItem(width, newItems))
        return rowItems
    }

    override fun dismissAlert() = showError.set(false)

    override fun getLoading(): ObservableBoolean = loading

    override fun getShowError(): ObservableBoolean = showError

    override fun getSpecialsList(): ObservableList<ManagerSpecialsRowItem> = specialsList

    override fun getAdapter(): RecyclerView.Adapter<ManagerSpecialsViewHolder> = adapter

}
