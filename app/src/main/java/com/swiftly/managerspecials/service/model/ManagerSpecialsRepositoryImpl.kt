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

package com.swiftly.managerspecials.service.model

import android.content.Context
import com.swiftly.managerspecials.R
import com.swiftly.managerspecials.service.ManagerSpecialsApi
import com.swiftly.managerspecials.service.ManagerSpecialsLocalDataSource
import com.swiftly.managerspecials.service.ManagerSpecialsRepository
import io.reactivex.Single

class ManagerSpecialsRepositoryImpl(private val api: ManagerSpecialsApi, private val localDataSource: ManagerSpecialsLocalDataSource, private val context: Context) : ManagerSpecialsRepository {

    override fun getManagerSpecials(): Single<ManagerSpecialsResponse> {
        //can handle persistence and local vs remote fetch here
        val sharedPrefs = context.getSharedPreferences(context.getString(R.string.local_data_prefs), Context.MODE_PRIVATE)
        val localData = sharedPrefs.getBoolean(context.getString(R.string.local_data), false)
        if (localData) {
            return ManagerSpecialsLocalDataSource().getLocalManagerSpecials()
        }
        return api.getManagerSpecials()
    }
}