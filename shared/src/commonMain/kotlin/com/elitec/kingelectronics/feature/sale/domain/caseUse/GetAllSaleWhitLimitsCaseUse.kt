package com.elitec.kingelectronics.feature.sale.domain.caseUse

import com.elitec.kingelectronics.feature.products.domain.entity.Product
import com.elitec.kingelectronics.feature.products.domain.repository.ProductRepository
import com.elitec.kingelectronics.feature.sale.domain.entity.Sale
import com.elitec.kingelectronics.feature.sale.domain.repository.SaleRepository

class GetAllSaleWhitLimitsCaseUse(
    private val repository: SaleRepository
) {
    suspend operator fun invoke(limit: Int, offset: Long): List<Sale> =
        repository.getAll(limit, offset)
}