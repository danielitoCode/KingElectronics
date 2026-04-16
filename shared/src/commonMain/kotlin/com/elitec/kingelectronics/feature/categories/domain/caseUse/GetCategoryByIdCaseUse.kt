package com.elitec.kingelectronics.feature.categories.domain.caseUse

import com.elitec.kingelectronics.feature.categories.domain.entity.Category
import com.elitec.kingelectronics.feature.categories.domain.repository.CategoryRepository

class GetCategoryByIdCaseUse(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(categoryId: Long): Result<Category?> = runCatching {
        repository.getCategoryById(categoryId)
    }
}