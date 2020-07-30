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

import com.swiftly.managerspecials.ui.model.ManagerSpecialsRowItem
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito

class ManagerSpecialsAdapterTest {

    private val data = listOf(ManagerSpecialsRowItem(16, listOf()))

    @Test
    fun testGetItemCount() {
        val adapter = ManagerSpecialsAdapter()
        adapter.setData(data)
        assertEquals(1, adapter.itemCount)
    }

    @Test(expected = UninitializedPropertyAccessException::class)
    fun testGetItemCount_UnsetData() {
        val adapter = ManagerSpecialsAdapter()
        adapter.itemCount
    }

    @Test
    fun testOnBindViewHolder() {
        val mockHolder = Mockito.mock(ManagerSpecialsViewHolder::class.java)
        val adapter = ManagerSpecialsAdapter()
        adapter.setData(data)
        adapter.onBindViewHolder(mockHolder, 0)
    }

    @Test(expected = UninitializedPropertyAccessException::class)
    fun testOnBindViewHolder_UnsetData() {
        val mockHolder = Mockito.mock(ManagerSpecialsViewHolder::class.java)
        val adapter = ManagerSpecialsAdapter()
        adapter.onBindViewHolder(mockHolder, 0)
    }
}

