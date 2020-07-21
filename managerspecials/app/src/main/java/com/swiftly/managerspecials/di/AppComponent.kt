package com.swiftly.managerspecials.di

import com.swiftly.managerspecials.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ServiceModule::class, SubcomponentsModule::class])
interface AppComponent {
    fun inject(target: MainActivity)

    fun managerSpecialsComponent(): ManagerSpecialsComponent.Factory
}