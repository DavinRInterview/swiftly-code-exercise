package com.swiftly.managerspecials.service

import com.swiftly.managerspecials.service.model.ManagerSpecialsResponse
import io.reactivex.Single

interface ManagerSpecialsRepository {

    fun getManagerSpecials(): Single<ManagerSpecialsResponse>
}