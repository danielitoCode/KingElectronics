package com.elitec.kingelectronics.feature.categories.domain.caseUse

import com.elitec.kingelectronics.feature.categories.domain.repository.CategoryRepository

class DeleteCategoryCaseUse(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(categoryId: Long): Result<Unit> = runCatching {
        repository.delete(categoryId)
    }
}