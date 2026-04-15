package com.elitec.kingelectronics.feature.sale.domain.entity

data class Sale(
    val id: String,
    val date: LocalDate,
    val amount: Double,
    val verified: BuyState,
    val products: List<SaleItem>,
    val userId: String,
    val customerName: String? = null,
    val deliveryType: DeliveryType? = null,
    val deliveryAddress: DeliveryAddress? = null
)