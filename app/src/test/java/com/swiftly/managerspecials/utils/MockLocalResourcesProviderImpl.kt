package com.swiftly.managerspecials.utils

import java.io.ByteArrayInputStream
import java.io.InputStream

class MockLocalResourcesProviderImpl : LocalResourcesProvider {

    val mockJson = "{\n" +
            "  \"canvasUnit\": 16,\n" +
            "  \"managerSpecials\": [\n" +
            "    {\n" +
            "      \"display_name\": \"Doodle Dish with Roasted Black Bean Sauce\",\n" +
            "      \"height\": 8,\n" +
            "      \"imageUrl\": \"https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/L.png\",\n" +
            "      \"original_price\": \"2.00\",\n" +
            "      \"price\": \"1.00\",\n" +
            "      \"width\": 16\n" +
            "    }" +
            "}"

    override fun getServiceFailureString(): String = "servicefailure"
    override fun getLocalDataString(): String = "localdata"
    override fun getLocalDataInputStream(): InputStream = ByteArrayInputStream(mockJson.toByteArray())
}