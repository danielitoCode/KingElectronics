package com.elitec.kingelectronics.feature.categories.domain.repository

import com.elitec.kingelectronics.feature.categories.domain.entity.Category

interface CategoryRepository {
    suspend fun save(newCategory: Category): Long
    suspend fun modify(categoryId: Long, modifiedCategory: Category)
    suspend fun getById(categoryId: Long): Category?
    suspend fun getAll(): List<Category>
    suspend fun delete(categoryId: Long)
}