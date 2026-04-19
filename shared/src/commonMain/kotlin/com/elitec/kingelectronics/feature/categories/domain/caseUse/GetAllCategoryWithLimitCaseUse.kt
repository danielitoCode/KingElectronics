package com.elitec.kingelectronics.feature.categories.domain.caseUse

import com.elitec.kingelectronics.feature.categories.domain.entity.Category
import com.elitec.kingelectronics.feature.categories.domain.repository.CategoryRepository

class GetAllCategoryWithLimitCaseUse(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(limit: Int, offset: Long): List<Category> =
        repository.getAll(limit, offset)
}