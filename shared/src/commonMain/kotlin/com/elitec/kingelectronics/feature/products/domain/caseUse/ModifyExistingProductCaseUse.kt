package com.elitec.kingelectronics.feature.products.domain.caseUse

import com.elitec.kingelectronics.feature.products.domain.entity.Product
import com.elitec.kingelectronics.feature.products.domain.repository.ProductRepository

class ModifyExistingProductCaseUse(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(
        productId: Long,
        modifiedProduct: Product
    ): Result<Unit> = runCatching {
        repository.modify(productId, modifiedProduct)
    }
}