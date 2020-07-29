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

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.swiftly.managerspecials.R
import com.swiftly.managerspecials.service.model.ManagerSpecialsItem
import com.swiftly.managerspecials.ui.model.ManagerSpecialsRowItem
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class ManagerSpecialsViewHolderTest {

    val mockInflater = Mockito.mock(LayoutInflater::class.java)
    val mockParent = Mockito.mock(ViewGroup::class.java)
    val mockContext = Mockito.mock(Context::class.java)
    val row = LinearLayout(mockContext)
    val view = ConstraintLayout(mockContext)

    val testViewHolder = ManagerSpecialsViewHolder(mockInflater, mockParent)

    val testRowItem = ManagerSpecialsRowItem(16, listOf(
        ManagerSpecialsItem(
        "Onion Flavored Rings",
        "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/J.png",
        "2.00",
        "1.00",
        8,
        8
    ),
    ManagerSpecialsItem(
        "Kikkoman Less Sodium Soy Sauce",
        "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/K.png",
        "2.00",
        "1.00",
        8,
        8
    )))

    @Before
    fun setup() {
        Mockito.`when`(mockInflater.inflate(R.layout.manager_special_row, mockParent, false)).thenReturn(row)
        Mockito.`when`(mockInflater.inflate(R.layout.manager_special_item, row, false)).thenReturn(view)
    }

    @Test
    fun testBind() {
        testViewHolder.bind(testRowItem)
    }
}