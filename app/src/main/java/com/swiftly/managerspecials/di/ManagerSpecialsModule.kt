package com.swiftly.managerspecials.di

import androidx.annotation.NonNull
import com.swiftly.managerspecials.service.ManagerSpecialsApi
import com.swiftly.managerspecials.service.ManagerSpecialsRepository
import com.swiftly.managerspecials.service.model.ManagerSpecialsRepositoryImpl
import com.swiftly.managerspecials.viewmodel.ManagerSpecialsViewModel
import com.swiftly.managerspecials.viewmodel.ManagerSpecialsViewModelImpl
import dagger.Module
import dagger.Provides

@Module
class ManagerSpecialsModule {

    @Provides
    @ManagerSpecialsScope
    fun provideManagerSpecialsRepository(@NonNull managerSpecialsApi: ManagerSpecialsApi) : ManagerSpecialsRepository
            = ManagerSpecialsRepositoryImpl(managerSpecialsApi)

    @Provides
    @ManagerSpecialsScope
    fun providesManagerSpecialsViewModule(@NonNull repository: ManagerSpecialsRepository): ManagerSpecialsViewModel
            = ManagerSpecialsViewModelImpl(repository)
}