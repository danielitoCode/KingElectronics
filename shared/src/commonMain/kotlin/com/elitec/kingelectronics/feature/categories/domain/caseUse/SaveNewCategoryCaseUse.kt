package com.elitec.kingelectronics.feature.categories.domain.caseUse

import com.elitec.kingelectronics.feature.categories.domain.entity.Category
import com.elitec.kingelectronics.feature.categories.domain.repository.CategoryRepository

class SaveNewCategoryCaseUse(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(newCategory: Category) = repository.save(newCategory)
}