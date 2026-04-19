package com.elitec.kingelectronics.feature.sale.domain.caseUse

import com.elitec.kingelectronics.feature.sale.domain.entity.Sale
import com.elitec.kingelectronics.feature.sale.domain.repository.SaleRepository

class SaveNewSaleCaseUse(
    private val repository: SaleRepository
) {
    suspend  operator fun invoke(newSale: Sale) = repository.save(newSale)
}