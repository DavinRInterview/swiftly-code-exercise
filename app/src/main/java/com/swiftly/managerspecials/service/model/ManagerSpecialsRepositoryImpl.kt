package com.swiftly.managerspecials.service.model

import com.swiftly.managerspecials.service.ManagerSpecialsApi
import com.swiftly.managerspecials.service.ManagerSpecialsLocal
import com.swiftly.managerspecials.service.ManagerSpecialsRepository
import com.swiftly.managerspecials.service.model.ManagerSpecialsResponse
import io.reactivex.Single

class ManagerSpecialsRepositoryImpl(val api: ManagerSpecialsApi) : ManagerSpecialsRepository {

    override fun getManagerSpecials(): Single<ManagerSpecialsResponse> {
        //can handle persistence and local vs remote fetch here
        //return ManagerSpecialsLocal().getLocalManagerSpecials()
        return api.getManagerSpecials()
    }
}