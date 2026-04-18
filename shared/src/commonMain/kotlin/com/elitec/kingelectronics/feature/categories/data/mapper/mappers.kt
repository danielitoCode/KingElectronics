package com.elitec.kingelectronics.feature.categories.data.mapper

import com.elitec.kingelectronics.feature.categories.data.dto.CategoryDto
import com.elitec.kingelectronics.feature.categories.domain.entity.Category

fun Category.toData(): CategoryDto = CategoryDto(
    id = id,
    name = name,
    iconUrl = iconUrl
)

fun CategoryDto.toDomain(): Category = Category(
    id = id,
    name = name,
    iconUrl = iconUrl ?: ""
)