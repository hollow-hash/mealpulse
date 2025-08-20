package com.example.mealpulse.models

import android.provider.ContactsContract.RawContacts.Data
import java.util.Date

data class FoodItem(
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
