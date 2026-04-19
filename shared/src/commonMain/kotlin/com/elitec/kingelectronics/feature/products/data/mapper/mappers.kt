package com.elitec.kingelectronics.feature.products.data.mapper

import com.elitec.kingelectronics.feature.categories.data.dto.CategoryDto
import com.elitec.kingelectronics.feature.categories.domain.entity.Category
import com.elitec.kingelectronics.feature.products.data.dto.ProductDto
import com.elitec.kingelectronics.feature.products.domain.entity.Product

fun Product.toData(): ProductDto = ProductDto(
    id = id,
    name = name,
    description = description,
    photoUrl = photoUrl,
    price = price,
    rating = rating,
    categoryId = categoryId
)

fun ProductDto.toDomain(): Product = Product(
    id = id,
    name = name,
    description = description,
    photoUrl = photoUrl,
    price = price,
    rating = rating,
    categoryId = categoryId
)