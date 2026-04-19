package com.elitec.kingelectronics.feature.sale.data.repository

import com.elitec.kingelectronics.feature.sale.data.mapper.toData
import com.elitec.kingelectronics.feature.sale.data.mapper.toDomain
import com.elitec.kingelectronics.feature.sale.domain.entity.Sale
import com.elitec.kingelectronics.feature.sale.domain.repository.SaleRepository

class SaleRepositoryImpl(
    private val saleService: SaleService
): SaleRepository {
    override suspend fun save(newSale: Sale): Long =
        saleService.create(newSale.toData())

    override suspend fun modify(
        saleId: Long,
        modifiedSale: Sale,
    ) {
        saleService.update(saleId,modifiedSale.toData())
    }

    override suspend fun getSaleById(saleId: Long): Sale? =
        saleService.read(saleId)?.toDomain()

    override suspend fun getSaleByIndex(index: Int): List<Sale> =
        TODO("Not yet implemented")

    override suspend fun getAllSales(): List<Sale> =
        saleService.readAll().map { it.toDomain() }

    override suspend fun deleteSale(saleId: Long) {
        saleService.delete(saleId)
    }
}