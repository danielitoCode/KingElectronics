package com.elitec.kingelectronics.feature.categories.data.repository

import com.elitec.kingelectronics.feature.categories.data.mapper.toData
import com.elitec.kingelectronics.feature.categories.data.mapper.toDomain
import com.elitec.kingelectronics.feature.categories.domain.entity.Category
import com.elitec.kingelectronics.feature.categories.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val categoryTable: CategoryService
): CategoryRepository {
    override suspend fun save(newCategory: Category): Long =
        categoryTable.create(
            newCategory.toData()
        )

    override suspend fun modify(categoryId: Long, modifiedCategory: Category) {
        categoryTable.update(categoryId,modifiedCategory.toData())
    }

    override suspend fun delete(categoryId: Long) {
        categoryTable.delete(categoryId)
    }

    override suspend fun getById(categoryId: Long): Category? =
        categoryTable.read(categoryId)?.toDomain()

    override suspend fun getAll(): List<Category> =
        categoryTable.readAll().map { it.toDomain() }
}