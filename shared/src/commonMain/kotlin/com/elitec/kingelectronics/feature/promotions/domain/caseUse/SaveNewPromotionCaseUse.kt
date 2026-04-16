package com.elitec.kingelectronics.feature.promotions.domain.caseUse

import com.elitec.kingelectronics.feature.promotions.domain.entity.Promotion
import com.elitec.kingelectronics.feature.promotions.domain.repository.PromotionRepository

class SaveNewPromotionCaseUse(
    private val repository: PromotionRepository
) {
    suspend operator fun invoke(newPromotion: Promotion) : Result<Unit> = runCatching {
        repository.save(newPromotion)
    }
}