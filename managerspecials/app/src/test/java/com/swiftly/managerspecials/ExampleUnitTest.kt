package com.swiftly.managerspecials

import com.swiftly.managerspecials.service.model.ManagerSpecialsItem
import com.swiftly.managerspecials.ui.model.ManagerSpecialsRowItem
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        val testList = listOf(
            ManagerSpecialsRowItem(16, listOf(
                ManagerSpecialsItem(
                    "Noodle Dish with Roasted Black Bean Sauce",
                    "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/L.png",
                    "2.00",
                    "1.00",
                    8,
                    16
                )
            )),
            ManagerSpecialsRowItem(16, listOf(
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
                )
            ))
        )
        assertEquals(4, 2 + 2)
    }
}