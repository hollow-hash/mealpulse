package com.example.mealpulse.models

data class CannedFood(
    var id: String? = null,
    val name: String? = null,
    val brand: String? = null,
    val quantity: String? = null,
    val unit: String? = null,
    val expirydate: String? = null,
    val purchasedate: String? = null,
    val location: String? = null,
    val imageUrl: String? = null
)
