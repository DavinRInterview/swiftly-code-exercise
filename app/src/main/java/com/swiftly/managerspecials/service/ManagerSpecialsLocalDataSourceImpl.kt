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

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.swiftly.managerspecials.R
import com.swiftly.managerspecials.service.model.ManagerSpecialsResponse
import com.swiftly.managerspecials.utils.LocalResourcesProvider
import io.reactivex.Single
import java.io.IOException
import java.util.Collections
import kotlin.concurrent.thread

class ManagerSpecialsLocalDataSourceImpl(private val localResourcesProvider: LocalResourcesProvider) :
    ManagerSpecialsLocalDataSource {
    override fun getLocalManagerSpecials() : Single<ManagerSpecialsResponse> {

        return Single.create {
            thread(start = true) {
                val jsonString: String
                try {
                    jsonString = localResourcesProvider.getLocalDataInputStream().bufferedReader().use { it.readText() }
                    val gson = Gson()
                    val responseType = object : TypeToken<ManagerSpecialsResponse>() {}.type
                    val managerSpecialsResponse : ManagerSpecialsResponse = gson.fromJson(jsonString, responseType)
                    it.onSuccess(managerSpecialsResponse)
                } catch (e: Exception) { //catches either IO or parse exception. This is debug, so not that worried about precision
                    it.onError(e)
                }
            }
        }
    }
}