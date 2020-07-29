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

import com.swiftly.managerspecials.rules.RxImmediateSchedulerRule
import com.swiftly.managerspecials.service.ManagerSpecialsRepository
import com.swiftly.managerspecials.service.model.ManagerSpecialsItem
import com.swiftly.managerspecials.service.model.ManagerSpecialsResponse
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.ClassRule
import org.junit.Test
import org.mockito.Mockito

class ManagerSpecialsViewModelImplTest {

    private val testList = listOf(
        ManagerSpecialsItem(
            "Noodle Dish with Roasted Black Bean Sauce",
            "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/L.png",
            "2.00",
            "1.00",
            8,
            16
        ),
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
        ),
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
            9
        )
    )

    private val repository: ManagerSpecialsRepository = Mockito.mock(ManagerSpecialsRepository::class.java)
    private val viewModel = ManagerSpecialsViewModelImpl(repository)

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Test
    fun testDataValidation() {
        val testListBadData = listOf(
            ManagerSpecialsItem(
                "Noodle Dish with Roasted Black Bean Sauce",
                "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/L.png",
                "2.00",
                "1.00",
                8,
                16
            ),
            ManagerSpecialsItem(
                "Onion Flavored Rings",
                "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/J.png",
                "2.00",
                "1.00",
                8,
                8
            ),
            ManagerSpecialsItem(
                "Kikkoman Less Sodium Soy Sauce Too big width",
                "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/K.png",
                "2.00",
                "1.00",
                8,
                17
            ),
            ManagerSpecialsItem(
                "Kikkoman Less Sodium Soy Sauce Too small height",
                "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/K.png",
                "2.00",
                "1.00",
                0,
                8
            ),
            ManagerSpecialsItem(
                "Kikkoman Less Sodium Soy Sauce",
                "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/K.png",
                "2.00",
                "1.00",
                8,
                8
            ),
            ManagerSpecialsItem(
                "Kikkoman Less Sodium Soy Sauce Too small width",
                "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/K.png",
                "2.00",
                "1.00",
                8,
                0
            )
        )

        val responseList = viewModel.dataValidation(16, testListBadData)
        assertEquals(3, responseList.size)
    }

    @Test
    fun testGroupItems() {
        var resultList = viewModel.groupItems(16, testList)
        assertEquals(4, resultList.size)
    }

    @Test
    fun testUpdateSpecialsData() {
        val testResponse = ManagerSpecialsResponse(16, testList)
        Mockito.`when`(repository.getManagerSpecials()).thenReturn(Single.just(testResponse))
        viewModel.updateSpecialsData()
        assertEquals(false, viewModel.getLoading().get())
        assertEquals(false, viewModel.getShowError().get())
        assertEquals(4, viewModel.getSpecialsList().size)
    }

    @Test
    fun testUpdateSpecialsData_Failure() {
        Mockito.`when`(repository.getManagerSpecials()).thenReturn(Single.error(Throwable()))
        viewModel.updateSpecialsData()
        assertEquals(false, viewModel.getLoading().get())
        assertEquals(true, viewModel.getShowError().get())
        assertEquals(0, viewModel.getSpecialsList().size)
        viewModel.dismissAlert()
        assertEquals(false, viewModel.getShowError().get())
    }
}