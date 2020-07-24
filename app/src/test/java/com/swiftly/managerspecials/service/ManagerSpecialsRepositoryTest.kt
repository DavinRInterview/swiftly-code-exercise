package com.swiftly.managerspecials.service

import android.content.Context
import android.content.SharedPreferences
import com.swiftly.managerspecials.R
import com.swiftly.managerspecials.service.model.ManagerSpecialsRepositoryImpl
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class ManagerSpecialsRepositoryTest {

    private val mockContext = Mockito.mock(Context::class.java);
    private val mockApi = Mockito.mock(ManagerSpecialsApi::class.java)
    private val mockLocalDataSource = Mockito.mock(ManagerSpecialsLocalDataSource::class.java)
    private val mockSharedPreferences = Mockito.mock(SharedPreferences::class.java)

    private val repo = ManagerSpecialsRepositoryImpl(mockApi, mockLocalDataSource, mockContext)

    @Before
    fun setUp() {
        Mockito.`when`(mockContext.getString(R.string.local_data_prefs)).thenReturn("localdataprefs")
        Mockito.`when`(mockContext.getSharedPreferences("localdataprefs", Context.MODE_PRIVATE)).thenReturn(mockSharedPreferences)
        Mockito.`when`(mockContext.getString(R.string.local_data)).thenReturn("localdata")
    }

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
}