package com.example.decathlon

import com.google.gson.annotations.SerializedName

/** Data class to store details about a Decathlon Product. */
data class ProductModel(
    @SerializedName("product_id")
    val id: String = "",
    @SerializedName("product_image_url")
    val imageUrl: String = "",
    @SerializedName("product_name")
    val name: String = "",
    @SerializedName("product_brand")
    val brand: String = "",
    @SerializedName("product_price")
    val price: Float = 0f
)
