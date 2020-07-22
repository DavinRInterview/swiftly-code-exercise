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

import com.swiftly.managerspecials.service.ManagerSpecialsRepository
import com.swiftly.managerspecials.service.model.ManagerSpecialsItem
import com.swiftly.managerspecials.ui.model.ManagerSpecialsRowItem
import io.reactivex.Single


class ManagerSpecialsViewModelImpl(val repository: ManagerSpecialsRepository) : ManagerSpecialsViewModel {

    override fun getManagerSpecials(): Single<List<ManagerSpecialsRowItem>> {
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
