package com.swiftly.managerspecials.utils

import java.io.InputStream

interface LocalResourcesProvider {
    fun getServiceFailureString(): String
    fun getLocalDataString(): String
    fun getLocalDataInputStream(): InputStream
}