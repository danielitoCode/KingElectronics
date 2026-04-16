package com.elitec.kingelectronics.feature.products.domain.caseUse

import com.elitec.kingelectronics.feature.products.domain.entity.Product
import com.elitec.kingelectronics.feature.products.domain.repository.ProductRepository

class GetAllProductByPage(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(index: Int): Result<List<Product>> = runCatching {
        repository.getAllProductWithPaging(index)
    }
}