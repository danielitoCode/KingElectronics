package com.elitec.kingelectronics.feature.products.domain.caseUse

import com.elitec.kingelectronics.feature.products.domain.entity.Product
import com.elitec.kingelectronics.feature.products.domain.repository.ProductRepository

class SaveNewProductCaseUse(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(newProduct: Product): Result<Unit> = runCatching {
        repository.save(newProduct)
    }
}