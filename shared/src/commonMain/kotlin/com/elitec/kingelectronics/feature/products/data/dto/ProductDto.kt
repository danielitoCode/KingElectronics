package com.elitec.kingelectronics.feature.products.data.dto

data class ProductDto(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val photoUrl: String,
    val categoryId: String,
    val rating: Double = 0.0,
    val photoLocalResource: Int? = null
) {
    init {
        require(id != "") { "The value of product identifier cant not by empty" }
        require(price >= 0.0) { "The price of product identifier cant not by a negative" }
    }
}