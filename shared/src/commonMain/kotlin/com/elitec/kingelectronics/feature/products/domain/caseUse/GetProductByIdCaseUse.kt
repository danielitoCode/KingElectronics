package com.elitec.kingelectronics.feature.products.domain.caseUse

import com.elitec.kingelectronics.feature.products.domain.entity.Product
import com.elitec.kingelectronics.feature.products.domain.repository.ProductRepository

class GetProductByIdCaseUse(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productId: Long): Result<Product?> = runCatching {
        repository.getProductById(productId)
    }
}