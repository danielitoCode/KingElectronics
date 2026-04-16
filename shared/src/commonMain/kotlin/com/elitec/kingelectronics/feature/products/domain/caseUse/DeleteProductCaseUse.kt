package com.elitec.kingelectronics.feature.products.domain.caseUse

import com.elitec.kingelectronics.feature.products.domain.repository.ProductRepository

class DeleteProductCaseUse(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productId: Long): Result<Unit> = runCatching {
        repository.deleteProduct(productId)
    }
}