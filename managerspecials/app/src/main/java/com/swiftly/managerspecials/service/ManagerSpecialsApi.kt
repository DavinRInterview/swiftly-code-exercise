package com.swiftly.managerspecials.service

import com.swiftly.managerspecials.service.model.ManagerSpecialsResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ManagerSpecialsApi {

    @GET("Swiftly-Systems/code-exercise-android/master/backup")
    fun getManagerSpecials(): Single<ManagerSpecialsResponse>
}