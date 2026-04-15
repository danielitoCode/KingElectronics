package com.elitec.kingelectronics.feature.promotions.domain.entity

data class Promotion(
    val id: String,
    val title: String,
    val description: String,
    val oldPrice: Double?,
    val newPrice: Double?,
    val photoUrl: String?
)