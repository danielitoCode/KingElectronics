package com.elitec.kingelectronics.feature.promotions.data.dto

data class PromotionDto(
    val id: Long,
    val title: String,
    val description: String,
    val oldPrice: Double?,
    val newPrice: Double?,
    val photoUrl: String?
)