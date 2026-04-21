package com.elitec.kingelectronics.feature.promotions.domain.entity

data class Promotion(
    val id: Long,
    val title: String,
    val description: String,
    val oldPrice: Double?,
    val newPrice: Double?,
    val photoUrl: String?
)