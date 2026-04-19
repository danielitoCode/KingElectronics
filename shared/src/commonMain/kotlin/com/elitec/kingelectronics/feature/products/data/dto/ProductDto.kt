package com.elitec.kingelectronics.feature.products.data.dto

data class ProductDto(
    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    val photoUrl: String,
    val categoryId: Long,
    val rating: Double = 0.0,
    val photoLocalResource: Int? = null
) {
    init {
        require(price >= 0.0) { "The price of product identifier cant not by a negative" }
    }
}