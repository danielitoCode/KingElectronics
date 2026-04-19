package com.elitec.kingelectronics.feature.products.domain.caseUse

import com.elitec.kingelectronics.feature.products.domain.entity.Product
import com.elitec.kingelectronics.feature.products.domain.repository.ProductRepository

class GetAllProductWithLimitsCaseUse(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(limit: Int, offset: Long): List<Product> =
        repository.getAll(limit, offset)
}