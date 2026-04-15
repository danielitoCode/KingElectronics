package com.elitec.kingelectronics.feature.categories.domain.repository

import com.elitec.kingelectronics.feature.categories.domain.entity.Category

interface CategoryRepository {
    suspend fun save(newCategory: Category)
    suspend fun modify(categoryId: Long, modifiedCategory: Category)
    suspend fun getCategoryById(categoryId: Long): Category?
    suspend fun getAllCategories(): List<Category>
    suspend fun deleteCategory(categoryId: Long)
}