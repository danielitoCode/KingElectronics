package com.elitec.kingelectronics.feature.sale.domain.entity

data class DeliveryAddress(
    val province: String,
    val municipality: String,
    val mainStreet: String,
    val betweenStreets: String? = null,
    val phone: String,
    val houseNumber: String,
    val referenceName: String? = null
)