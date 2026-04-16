package com.elitec.kingelectronics.feature.promotions.domain.caseUse

import com.elitec.kingelectronics.feature.promotions.domain.entity.Promotion
import com.elitec.kingelectronics.feature.promotions.domain.repository.PromotionRepository

class GetPromotionsByIdCaseUse(
    private val repository: PromotionRepository
) {
    suspend operator fun invoke(promotionId: Long): Result<Promotion?> = runCatching {
        repository.getPromotionById(promotionId)
    }
}