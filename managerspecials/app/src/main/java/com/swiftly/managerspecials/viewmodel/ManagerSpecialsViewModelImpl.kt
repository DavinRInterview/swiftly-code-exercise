package com.swiftly.managerspecials.viewmodel

import com.swiftly.managerspecials.service.ManagerSpecialsRepository
import com.swiftly.managerspecials.service.model.ManagerSpecialsItem
import com.swiftly.managerspecials.ui.model.ManagerSpecialsRowItem


class ManagerSpecialsViewModelImpl(val repository: ManagerSpecialsRepository) : ManagerSpecialsViewModel {

    override fun getManagerSpecials() {
        //execute data processing here
        repository.getManagerSpecials()
    }

    private fun groupItems(width: Int, items: List<ManagerSpecialsItem>) : List<ManagerSpecialsRowItem> {
        val rowItems = ArrayList<ManagerSpecialsRowItem>()
        var n = 0
        while (n < items.size) {
            val endIndex = findEndIndex(width, items, n)
            val specials = ArrayList<ManagerSpecialsItem>()
            if (endIndex > 0) {
                for (m in n..endIndex)
                    specials.add(items[m])
                rowItems.add(ManagerSpecialsRowItem(width,specials))
            } else {
                specials.add(items[n])
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
                return -1
            }
        }
        return -1
    }
}
