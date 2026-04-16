package com.elitec.kingelectronics.feature.categories.domain.caseUse

import com.elitec.kingelectronics.feature.categories.domain.entity.Category
import com.elitec.kingelectronics.feature.categories.domain.repository.CategoryRepository

class ModifyCategoryCaseUse(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(
        categoryId: Long,
        modifiedCategory: Category
    ): Result<Unit> = runCatching {
        repository.modify(categoryId, modifiedCategory)
    }
}