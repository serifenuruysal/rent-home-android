package com.ebayk.domain.entity

import com.google.gson.annotations.SerializedName

data class Address(
    val city: String?,
    val latitude: String?,
    val longitude: String?,
    val street: String?,
    @SerializedName("zip-code")
    val zipCode: String?
)