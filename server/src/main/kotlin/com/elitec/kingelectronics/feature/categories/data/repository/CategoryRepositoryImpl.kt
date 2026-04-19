package com.elitec.kingelectronics.feature.categories.data.repository

import com.elitec.kingelectronics.feature.categories.data.mapper.toData
import com.elitec.kingelectronics.feature.categories.data.mapper.toDomain
import com.elitec.kingelectronics.feature.categories.domain.entity.Category
import com.elitec.kingelectronics.feature.categories.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val categoryDataSource: CategoryDataSource
): CategoryRepository {
    override suspend fun save(newCategory: Category): Long =
        categoryDataSource.create(
            newCategory.toData()
        )

    override suspend fun modify(categoryId: Long, modifiedCategory: Category) {
        categoryDataSource.update(categoryId,modifiedCategory.toData())
    }

    override suspend fun delete(categoryId: Long) {
        categoryDataSource.delete(categoryId)
    }

    override suspend fun getById(categoryId: Long): Category? =
        categoryDataSource.read(categoryId)?.toDomain()

    override suspend fun getAll(): List<Category> =
        categoryDataSource.readAll().map { it.toDomain() }

    override suspend fun getAll(limit: Int, offset: Long, ): List<Category> =
        categoryDataSource.getAll(limit, offset).map { it.toDomain() }
}