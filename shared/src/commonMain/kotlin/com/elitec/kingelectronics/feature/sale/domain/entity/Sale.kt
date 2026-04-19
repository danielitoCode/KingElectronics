package com.elitec.kingelectronics.feature.sale.domain.entity

data class Sale(
    val id: Long,
    // val date: LocalDate,
    val date: String,
    val amount: Double,
    val verified: BuyState,
    val products: List<SaleItem>,
    val userId: Long,
    val customerName: String? = null,
    val deliveryType: DeliveryType? = null,
    val deliveryAddress: DeliveryAddress? = null
)