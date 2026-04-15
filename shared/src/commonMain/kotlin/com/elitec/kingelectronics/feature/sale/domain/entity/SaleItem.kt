package com.elitec.kingelectronics.feature.sale.domain.entity

data class SaleItem(
    val productId: String,
    val quantity: Int,
    val productName: String? = null
) {
    init {
        require(productId.isNotBlank()) { "Product id cannot be blank" }
        require(quantity > 0) { "Quantity must be greater than 0" }
    }
}
