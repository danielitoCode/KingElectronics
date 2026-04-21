package com.elitec.kingelectronics.feature.promotions.data.repository

import com.elitec.kingelectronics.feature.promotions.data.mappers.toDomain
import com.elitec.kingelectronics.feature.promotions.data.mappers.toDto
import com.elitec.kingelectronics.feature.promotions.domain.entity.Promotion
import com.elitec.kingelectronics.feature.promotions.domain.repository.PromotionRepository

class PromotionRepositoryImpl(
    private val dataSource: PromotionDataSource
): PromotionRepository {
    override suspend fun save(newPromotion: Promotion): Long =
        dataSource.create(newPromotion.toDto())

    override suspend fun deletePromotion(promotionId: Long) =
        dataSource.delete(promotionId)

    override suspend fun getPromotionById(promotionId: Long): Promotion? =
        dataSource.read(promotionId)?.toDomain()

    override suspend fun getAllPromotions(): List<Promotion> =
        dataSource.readAll().map { it.toDomain() }

    override suspend fun modify(
        promotionId: Long,
        modifiedPromotion: Promotion,
    ) = dataSource.update(promotionId, modifiedPromotion.toDto())
}