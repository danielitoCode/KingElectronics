package com.elitec.kingelectronics.feature.promotions.domain.repository

import com.elitec.kingelectronics.feature.promotions.domain.entity.Promotion

interface PromotionRepository {
    suspend fun save(newPromotion: Promotion)
    suspend fun deletePromotion(promotionId: Long)
    suspend fun getPromotionById(promotion: Long): Promotion?
    suspend fun getAllPromotions(): List<Promotion>
    suspend fun modify(promotionId: Long, modifiedPromotion: Promotion)
}