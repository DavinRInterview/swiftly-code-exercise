package com.swiftly.managerspecials.viewmodel

import com.swiftly.managerspecials.service.ManagerSpecialsRepository
import com.swiftly.managerspecials.service.model.ManagerSpecialsItem
import com.swiftly.managerspecials.ui.model.ManagerSpecialsRowItem
import io.reactivex.Single


class ManagerSpecialsViewModelImpl(val repository: ManagerSpecialsRepository) : ManagerSpecialsViewModel {

    override fun getManagerSpecials(): Single<List<ManagerSpecialsRowItem>> {
        //execute data processing here
        return repository.getManagerSpecials().map {
            groupItems(it.canvasUnit, it.managerSpecials)
        }
    }

    private fun groupItems(width: Int, items: List<ManagerSpecialsItem>) : List<ManagerSpecialsRowItem> {
        val rowItems = ArrayList<ManagerSpecialsRowItem>()
        var n = 0
        while (n < items.size) {
            val endIndex = findEndIndex(width, items, n)
            if (endIndex > n) {
                val specials = ArrayList<ManagerSpecialsItem>()
                for (m in n..endIndex)
                    specials.add(items[m])
                rowItems.add(ManagerSpecialsRowItem(width,specials))
                n = endIndex+1
            } else {
                val specials = ArrayList<ManagerSpecialsItem>()
                specials.add(items[n])
                rowItems.add(ManagerSpecialsRowItem(width, specials))
                n++
            }
        }
        return rowItems
    }

    private fun findEndIndex(width: Int, items: List<ManagerSpecialsItem>, index: Int) : Int {
        var currWidth = 0;
        for (m in index..items.size-1) {
            currWidth += items[m].width
            if (currWidth == width) {
                return m
            }
            if (currWidth > width) {
                return index
            }
        }
        return -1
    }
}
