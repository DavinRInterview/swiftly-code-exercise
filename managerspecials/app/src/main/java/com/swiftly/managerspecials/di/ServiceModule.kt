package com.swiftly.managerspecials.di

import androidx.annotation.NonNull
import com.swiftly.managerspecials.service.ManagerSpecialsApi
import com.swiftly.managerspecials.service.ManagerSpecialsRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build();
    }

    @Provides
    @Singleton
    fun provideRetrofit(@NonNull okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideManagerSpecialsApi(@NonNull retrofit: Retrofit) : ManagerSpecialsApi {
        return retrofit.create(ManagerSpecialsApi::class.java)
    }
}