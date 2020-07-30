package com.swiftly.managerspecials.service

import android.content.SharedPreferences
import com.swiftly.managerspecials.service.model.ManagerSpecialsRepositoryImpl
import com.swiftly.managerspecials.service.model.ManagerSpecialsResponse
import com.swiftly.managerspecials.utils.MockLocalResourcesProviderImpl
import io.reactivex.Single
import org.junit.Test
import org.junit.Assert.assertEquals
import org.mockito.Mockito
import org.mockito.Mockito.verify

class ManagerSpecialsRepositoryTest {

    private val mockApi = Mockito.mock(ManagerSpecialsApi::class.java)
    private val mockLocalDataSource = Mockito.mock(ManagerSpecialsLocalDataSource::class.java)
    private val mockSharedPreferences = Mockito.mock(SharedPreferences::class.java)

    private val repo = ManagerSpecialsRepositoryImpl(mockApi, mockLocalDataSource, MockLocalResourcesProviderImpl(), mockSharedPreferences)

    @Test
    fun testGetManagerSpecialsRemote() {
        Mockito.`when`(mockSharedPreferences.getBoolean("localdata",false)).thenReturn(false)
        repo.getManagerSpecials()
        verify(mockApi).getManagerSpecials()
    }

    @Test
    fun testGetManagerSpecialsLocal() {
        Mockito.`when`(mockSharedPreferences.getBoolean("localdata",false)).thenReturn(true)
        repo.getManagerSpecials()
        verify(mockLocalDataSource).getLocalManagerSpecials()
    }

    @Test
    fun testGetManagerSpecialsFailure() {
        Mockito.`when`(mockSharedPreferences.getBoolean("servicefailure", false)).thenReturn(true)
        repo.getManagerSpecials()
    }
}