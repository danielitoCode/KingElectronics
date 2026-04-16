package com.elitec.kingelectronics.feature.sale.domain.repository

import com.elitec.kingelectronics.feature.sale.domain.entity.Sale

interface SaleRepository {
    suspend fun save(newSale: Sale)
    suspend fun modify(saleId: Long, modifiedSale: Sale)
    suspend fun getSaleById(saleId: Long): Sale?
    suspend fun getSaleByIndex(index: Int): List<Sale>
    suspend fun getAllSales(): List<Sale>
    suspend fun deleteSale(saleId: Long)
}