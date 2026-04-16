package com.elitec.kingelectronics.feature.promotions.domain.caseUse

import com.elitec.kingelectronics.feature.promotions.domain.entity.Promotion
import com.elitec.kingelectronics.feature.promotions.domain.repository.PromotionRepository

class ModifyExistingPromotionCaseUse(
    private val repository: PromotionRepository
) {
    suspend operator fun invoke(
        promotionId: Long,
        modifiedPromotion: Promotion
    ): Result<Unit> = runCatching {
        repository.modify(promotionId, modifiedPromotion)
    }
}