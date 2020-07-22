package com.swiftly.managerspecials.di

import com.swiftly.managerspecials.ui.ManagerSpecialsFragment
import dagger.Component
import dagger.Subcomponent

//@Component(dependencies = [AppComponent::class], modules = [ManagerSpecialsModule::class])
@Subcomponent(modules = [ManagerSpecialsModule::class])
@ManagerSpecialsScope
interface ManagerSpecialsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ManagerSpecialsComponent
    }

    fun inject(managerSpecialsFragment: ManagerSpecialsFragment)
}
