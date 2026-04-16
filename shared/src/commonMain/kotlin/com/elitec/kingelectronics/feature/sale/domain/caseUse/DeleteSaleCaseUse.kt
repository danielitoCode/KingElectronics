package com.elitec.kingelectronics.feature.sale.domain.caseUse

import com.elitec.kingelectronics.feature.sale.domain.repository.SaleRepository

class DeleteSaleCaseUse(
    private val repository: SaleRepository
) {
    suspend operator fun invoke(saleId: Long): Result<Unit> = runCatching {
        repository.deleteSale(saleId)
    }
}