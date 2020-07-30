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

import android.content.SharedPreferences
import com.swiftly.managerspecials.BuildConfig
import com.swiftly.managerspecials.service.ManagerSpecialsApi
import com.swiftly.managerspecials.service.ManagerSpecialsLocalDataSource
import com.swiftly.managerspecials.service.ManagerSpecialsRepository
import com.swiftly.managerspecials.utils.LocalResourcesProvider
import io.reactivex.Single

class ManagerSpecialsRepositoryImpl(private val api: ManagerSpecialsApi, private val localDataSource: ManagerSpecialsLocalDataSource,
                                    private val localResourcesProvider: LocalResourcesProvider, private val sharedPrefs: SharedPreferences) : ManagerSpecialsRepository {

    override fun getManagerSpecials(): Single<ManagerSpecialsResponse> {
        if (BuildConfig.DEBUG) {
            val testServiceFailure =
                sharedPrefs.getBoolean(localResourcesProvider.getServiceFailureString(), false)
            if (testServiceFailure) {
                return Single.error(Throwable())
            }
            val localData = sharedPrefs.getBoolean(localResourcesProvider.getLocalDataString(), false)
            if (localData) {
                return localDataSource.getLocalManagerSpecials()
            }
        }
        return api.getManagerSpecials()
    }
}