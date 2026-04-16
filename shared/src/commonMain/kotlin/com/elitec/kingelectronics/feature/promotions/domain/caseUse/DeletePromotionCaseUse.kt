package com.elitec.kingelectronics.feature.promotions.domain.caseUse

import com.elitec.kingelectronics.feature.promotions.domain.repository.PromotionRepository

class DeletePromotionCaseUse(
    private val repository: PromotionRepository
) {
    suspend operator fun invoke(promotionId: Long) : Result<Unit> = runCatching {
        repository.deletePromotion(promotionId)
    }
}