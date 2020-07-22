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

package com.swiftly.managerspecials.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.swiftly.managerspecials.service.model.ManagerSpecialsResponse
import io.reactivex.Single

class ManagerSpecialsLocal {
    fun getLocalManagerSpecials() : Single<ManagerSpecialsResponse> {

        val jsonString = "{\n" +
                "  \"canvasUnit\": 16,\n" +
                "  \"managerSpecials\": [\n" +
                "    {\n" +
                "      \"display_name\": \"Noodle Dish with Roasted Black Bean Sauce\",\n" +
                "      \"height\": 8,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/L.png\",\n" +
                "      \"original_price\": \"2.00\",\n" +
                "      \"price\": \"1.00\",\n" +
                "      \"width\": 16\n" +
                "    },\n" +
                "    {\n" +
                "      \"display_name\": \"Onion Flavored Rings\",\n" +
                "      \"height\": 10,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/J.png\",\n" +
                "      \"original_price\": \"2.00\",\n" +
                "      \"price\": \"1.00\",\n" +
                "      \"width\": 8\n" +
                "    },\n" +
                "    {\n" +
                "      \"display_name\": \"Kikkoman Less Sodium Soy Sauce\",\n" +
                "      \"height\": 8,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/K.png\",\n" +
                "      \"original_price\": \"2.00\",\n" +
                "      \"price\": \"1.00\",\n" +
                "      \"width\": 8\n" +
                "    },\n" +
                "    {\n" +
                "      \"display_name\": \"Organic Romaine Hearts\",\n" +
                "      \"height\": 4,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/L.png\",\n" +
                "      \"original_price\": \"3.92\",\n" +
                "      \"price\": \"3.92\",\n" +
                "      \"width\": 14\n" +
                "    },\n" +
                "    {\n" +
                "      \"display_name\": \"Navel Oranges 4LBS\",\n" +
                "      \"height\": 4,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/L.png\",\n" +
                "      \"original_price\": \"2.23\",\n" +
                "      \"price\": \"1.29\",\n" +
                "      \"width\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"display_name\": \"Cheddar & Sour Ruffles\",\n" +
                "      \"height\": 6,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/K.png\",\n" +
                "      \"original_price\": \"1.49\",\n" +
                "      \"price\": \"1.49\",\n" +
                "      \"width\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"display_name\": \"Doritos Ranch\",\n" +
                "      \"height\": 6,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/K.png\",\n" +
                "      \"original_price\": \"1.49\",\n" +
                "      \"price\": \"1.49\",\n" +
                "      \"width\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"display_name\": \"Lindt Hello Cookies & Cream 1.4 oz\",\n" +
                "      \"height\": 6,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/K.png\",\n" +
                "      \"original_price\": \"1.49\",\n" +
                "      \"price\": \"1.49\",\n" +
                "      \"width\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"display_name\": \"Kinder Chocolate 50G\",\n" +
                "      \"height\": 16,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/J.png\",\n" +
                "      \"original_price\": \"1.99\",\n" +
                "      \"price\": \"1.29\",\n" +
                "      \"width\": 16\n" +
                "    },\n" +
                "    {\n" +
                "      \"display_name\": \"Organic Red Bells Peppers\",\n" +
                "      \"height\": 16,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/L.png\",\n" +
                "      \"original_price\": \"1.29\",\n" +
                "      \"price\": \"1.29\",\n" +
                "      \"width\": 16\n" +
                "    },\n" +
                "    {\n" +
                "      \"display_name\": \"Ferrero Rocher 7.9oz Gift Cube\",\n" +
                "      \"height\": 8,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/L.png\",\n" +
                "      \"original_price\": \"1.29\",\n" +
                "      \"price\": \"1.29\",\n" +
                "      \"width\": 9\n" +
                "    },\n" +
                "    {\n" +
                "      \"display_name\": \"Reynolds Oven Bags 2CT\",\n" +
                "      \"height\": 8,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/K.png\",\n" +
                "      \"original_price\": \"1.29\",\n" +
                "      \"price\": \"1.29\",\n" +
                "      \"width\": 5\n" +
                "    },\n" +
                "    {\n" +
                "      \"display_name\": \"Blue Diamond Almond Roasted Salted 6oz\",\n" +
                "      \"height\": 3,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/J.png\",\n" +
                "      \"original_price\": \"4.29\",\n" +
                "      \"price\": \"4.29\",\n" +
                "      \"width\": 3\n" +
                "    },\n" +
                "    {\n" +
                "      \"display_name\": \"Blue Diamond Artisan Nut Thin Sesame 4.24oz\",\n" +
                "      \"height\": 3,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/J.png\",\n" +
                "      \"original_price\": \"3.99\",\n" +
                "      \"price\": \"3.99\",\n" +
                "      \"width\": 3\n" +
                "    },\n" +
                "    {\n" +
                "      \"display_name\": \"Blue Diamond Artisan Nut Thin Sesame 4.24oz\",\n" +
                "      \"height\": 3,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/J.png\",\n" +
                "      \"original_price\": \"3.99\",\n" +
                "      \"price\": \"3.99\",\n" +
                "      \"width\": 3\n" +
                "    },\n" +
                "    {\n" +
                "      \"display_name\": \"Mochi Ice Cream Cookies N Cream\",\n" +
                "      \"height\": 4,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/L.png\",\n" +
                "      \"original_price\": \"4.99\",\n" +
                "      \"price\": \"2.99\",\n" +
                "      \"width\": 3\n" +
                "    },\n" +
                "    {\n" +
                "      \"display_name\": \"Alessi Vinegar Balsamic White 12.75oz\",\n" +
                "      \"height\": 4,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/K.png\",\n" +
                "      \"original_price\": \"4.49\",\n" +
                "      \"price\": \"4.49\",\n" +
                "      \"width\": 12\n" +
                "    },\n" +
                "    {\n" +
                "      \"display_name\": \"Alessi Pesto 3.5oz\",\n" +
                "      \"height\": 4,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/J.png\",\n" +
                "      \"original_price\": \"3.99\",\n" +
                "      \"price\": \"3.99\",\n" +
                "      \"width\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"display_name\": \"Hidden Valley Ranch Cucumber\",\n" +
                "      \"height\": 5,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/J.png\",\n" +
                "      \"original_price\": \"4.59\",\n" +
                "      \"price\": \"4.59\",\n" +
                "      \"width\": 5\n" +
                "    },\n" +
                "    {\n" +
                "      \"display_name\": \"Arrowhead Water 1L\",\n" +
                "      \"height\": 5,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/K.png\",\n" +
                "      \"original_price\": \"0.99\",\n" +
                "      \"price\": \"0.99\",\n" +
                "      \"width\": 5\n" +
                "    },\n" +
                "    {\n" +
                "      \"display_name\": \"Arrowhead Mountain Spring Water 1.5 L\",\n" +
                "      \"height\": 5,\n" +
                "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/L.png\",\n" +
                "      \"original_price\": \"1.29\",\n" +
                "      \"price\": \"1.01\",\n" +
                "      \"width\": 5\n" +
                "    }\n" +
                "  ]\n" +
                "}"

        val gson = Gson()
        val responseType = object : TypeToken<ManagerSpecialsResponse>() {}.type
        val response : ManagerSpecialsResponse = gson.fromJson(jsonString, responseType)
        return Single.just(response)
    }

}