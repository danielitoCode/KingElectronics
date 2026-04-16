package com.elitec.kingelectronics.feature.sale.domain.caseUse

import com.elitec.kingelectronics.feature.sale.domain.entity.Sale
import com.elitec.kingelectronics.feature.sale.domain.repository.SaleRepository

class GetAllSaleByPageCaseUse(
    private val repository: SaleRepository
) {
    suspend operator fun invoke(index: Int): Result<List<Sale>> = runCatching {
        repository.getSaleByIndex(index)
    }
}