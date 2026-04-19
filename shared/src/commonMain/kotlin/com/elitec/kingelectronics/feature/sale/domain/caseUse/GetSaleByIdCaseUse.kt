package com.elitec.kingelectronics.feature.sale.domain.caseUse

import com.elitec.kingelectronics.feature.sale.domain.entity.Sale
import com.elitec.kingelectronics.feature.sale.domain.repository.SaleRepository

class GetSaleByIdCaseUse(
    private val repository: SaleRepository
) {
    suspend operator fun invoke(saleId: Long): Sale? = repository.getSaleById(saleId)

}