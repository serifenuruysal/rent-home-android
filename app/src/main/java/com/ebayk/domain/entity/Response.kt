package com.ebayk.domain.entity

import com.google.gson.annotations.SerializedName

data class Response(
    val address: Address?,
    val attributes: List<Attribute>?,
    val description: String?,
    val documents: List<Document>?,
    val features: List<String>?,
    val id: String?,
    val pictures: List<String>?,
    @SerializedName("posted-date-time")
    val postedDateTime: String?,
    val price: Price?,
    val title: String?,
    val visits: Int
)