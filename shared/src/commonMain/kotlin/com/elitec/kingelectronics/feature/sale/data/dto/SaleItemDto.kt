package com.elitec.kingelectronics.feature.sale.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SaleItemDto(
    val productId: String,
    val quantity: Int,
    val productName: String? = null
)