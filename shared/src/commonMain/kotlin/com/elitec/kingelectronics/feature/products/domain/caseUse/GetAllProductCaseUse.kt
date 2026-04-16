package com.elitec.kingelectronics.feature.products.domain.caseUse

import com.elitec.kingelectronics.feature.products.domain.entity.Product
import com.elitec.kingelectronics.feature.products.domain.repository.ProductRepository

class GetAllProductCaseUse(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): Result<List<Product>> = runCatching {
        repository.getAllProducts()
    }
}