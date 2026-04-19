package com.elitec.kingelectronics.feature.sale.domain.repository

import com.elitec.kingelectronics.feature.products.domain.entity.Product
import com.elitec.kingelectronics.feature.sale.domain.entity.Sale

interface SaleRepository {
    suspend fun save(newSale: Sale): Long
    suspend fun modify(saleId: Long, modifiedSale: Sale)
    suspend fun getSaleById(saleId: Long): Sale?
    suspend fun getAll(limit: Int, offset: Long): List<Sale>
    suspend fun getAllSales(): List<Sale>
    suspend fun deleteSale(saleId: Long)
}