package com.swiftly.managerspecials.viewmodel

import com.swiftly.managerspecials.ui.model.ManagerSpecialsRowItem
import io.reactivex.Single

interface ManagerSpecialsViewModel {
    fun getManagerSpecials(): Single<List<ManagerSpecialsRowItem>>
}