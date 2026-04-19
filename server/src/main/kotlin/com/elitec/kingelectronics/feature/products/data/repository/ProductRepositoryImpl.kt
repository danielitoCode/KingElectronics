package com.elitec.kingelectronics.feature.products.data.repository

import com.elitec.kingelectronics.feature.products.data.mapper.toData
import com.elitec.kingelectronics.feature.products.data.mapper.toDomain
import com.elitec.kingelectronics.feature.products.domain.entity.Product
import com.elitec.kingelectronics.feature.products.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val productDataSource: ProductDataSource
): ProductRepository {
    override suspend fun save(newProduct: Product): Long =
        productDataSource.create(newProduct.toData())

    override suspend fun modify(
        productId: Long,
        modifiedProduct: Product,
    ) {
        productDataSource.update(productId, modifiedProduct.toData())
    }

    override suspend fun getProductById(productId: Long): Product? =
        productDataSource.read(productId)?.toDomain()

    override suspend fun getAllProducts(): List<Product> =
        productDataSource.readAll().map { it.toDomain() }

    override suspend fun getAll(limit: Int, offset: Long, ): List<Product> =
        productDataSource.getAll(limit, offset).map { it.toDomain() }

    override suspend fun deleteProduct(productId: Long) {
        productDataSource.delete(productId)
    }
}