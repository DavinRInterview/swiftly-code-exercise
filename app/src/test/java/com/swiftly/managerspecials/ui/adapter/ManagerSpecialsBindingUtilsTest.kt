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

import androidx.recyclerview.widget.RecyclerView
import com.swiftly.managerspecials.ui.model.ManagerSpecialsRowItem
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito

class ManagerSpecialsBindingUtilsTest {

    val mockRecyclerView = Mockito.mock(RecyclerView::class.java)

    @Test
    fun testSetRecyclerViewData() {
        val adapter = ManagerSpecialsAdapter()
        val dataList : List<ManagerSpecialsRowItem> = listOf()
        setRecyclerViewData(mockRecyclerView, adapter, dataList)
        assertEquals(0, adapter.itemCount)
    }

    @Test(expected = TypeCastException::class)
    fun testSetRecyclerViewData_NotBindable() {
        val adapter = Mockito.mock(RecyclerView.Adapter::class.java)
        val dataList : List<ManagerSpecialsRowItem> = listOf()
        setRecyclerViewData(mockRecyclerView, adapter, dataList)
    }
}

