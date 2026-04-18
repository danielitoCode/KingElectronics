package com.elitec.kingelectronics.feature.categories.domain.caseUse

import com.elitec.kingelectronics.feature.categories.domain.entity.Category
import com.elitec.kingelectronics.feature.categories.domain.repository.CategoryRepository

class GetAllCategoryCaseUse(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(): Result<List<Category>> = runCatching {
        repository.getAll()
    }
}