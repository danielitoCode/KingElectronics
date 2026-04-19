package com.elitec.kingelectronics.feature.sale.data.repository

import com.elitec.kingelectronics.feature.sale.data.mapper.toData
import com.elitec.kingelectronics.feature.sale.data.mapper.toDomain
import com.elitec.kingelectronics.feature.sale.domain.entity.Sale
import com.elitec.kingelectronics.feature.sale.domain.repository.SaleRepository

class SaleRepositoryImpl(
    private val salesDataSource: SalesDataSource
): SaleRepository {
    override suspend fun save(newSale: Sale): Long =
        salesDataSource.create(newSale.toData())

    override suspend fun modify(
        saleId: Long,
        modifiedSale: Sale,
    ) {
        salesDataSource.update(saleId,modifiedSale.toData())
    }

    override suspend fun getSaleById(saleId: Long): Sale? =
        salesDataSource.read(saleId)?.toDomain()

    override suspend fun getAll(limit: Int, offset: Long, ): List<Sale>  =
        salesDataSource.getAll(limit,offset).map { it.toDomain() }

    override suspend fun getAllSales(): List<Sale> =
        salesDataSource.readAll().map { it.toDomain() }

    override suspend fun deleteSale(saleId: Long) {
        salesDataSource.delete(saleId)
    }
}