package com.elitec.kingelectronics.feature.categories.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val id: Long,
    val name: String,
    val iconUrl: String?
)