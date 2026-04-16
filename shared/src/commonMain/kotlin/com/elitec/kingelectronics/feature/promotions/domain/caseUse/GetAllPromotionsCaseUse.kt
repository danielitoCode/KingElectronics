package com.elitec.kingelectronics.feature.promotions.domain.caseUse

import com.elitec.kingelectronics.feature.promotions.domain.entity.Promotion
import com.elitec.kingelectronics.feature.promotions.domain.repository.PromotionRepository

class GetAllPromotionsCaseUse(
    private val repository: PromotionRepository
) {
    suspend operator fun invoke(): Result<List<Promotion>> = runCatching {
        repository.getAllPromotions()
    }
}