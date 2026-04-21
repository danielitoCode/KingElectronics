package com.elitec.kingelectronics.feature.promotions.data.mappers

import com.elitec.kingelectronics.feature.promotions.data.dto.PromotionDto
import com.elitec.kingelectronics.feature.promotions.domain.entity.Promotion

fun Promotion.toDto(): PromotionDto =
    PromotionDto(
        id = this.id,
        title = this.title,
        description = this.description,
        oldPrice = this.oldPrice,
        newPrice = this.newPrice,
        photoUrl = this.photoUrl
    )

fun PromotionDto.toDomain(): Promotion =
    Promotion(
        id = this.id,
        title = this.title,
        description = this.description,
        oldPrice = this.oldPrice,
        newPrice = this.newPrice,
        photoUrl = this.photoUrl
    )