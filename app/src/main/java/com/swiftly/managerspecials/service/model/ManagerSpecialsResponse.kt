package com.swiftly.managerspecials.service.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ManagerSpecialsResponse(
    val canvasUnit: Int,
    val managerSpecials : List<ManagerSpecialsItem>
) : Parcelable

@Parcelize
data class ManagerSpecialsItem(
    @SerializedName("display_name")val displayName: String,
    val imageUrl: String,
    @SerializedName("original_price") val originalPrice: String,
    val price: String,
    val height: Int,
    val width: Int) : Parcelable