package com.swiftly.managerspecials

import android.app.Application
import com.swiftly.managerspecials.di.AppComponent
import com.swiftly.managerspecials.di.AppModule
import com.swiftly.managerspecials.di.DaggerAppComponent

class ManagerSpecialsApplication : Application() {

    lateinit var appComponent: AppComponent

    /*
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector
     */

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }

    private fun initDagger(app: ManagerSpecialsApplication) : AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
}