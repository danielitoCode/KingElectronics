package com.elitec.kingelectronics.feature.products.domain.repository

import com.elitec.kingelectronics.feature.categories.domain.entity.Category
import com.elitec.kingelectronics.feature.products.domain.entity.Product

interface ProductRepository {
    suspend fun save(newProduct: Product): Long
    suspend fun modify(productId: Long, modifiedProduct: Product)
    suspend fun getProductById(productId: Long): Product?
    suspend fun getAllProducts(): List<Product>
    suspend fun getAll(limit: Int, offset: Long): List<Product>
    suspend fun deleteProduct(productId: Long)
}