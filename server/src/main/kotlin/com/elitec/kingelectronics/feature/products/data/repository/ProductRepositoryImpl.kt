package com.elitec.kingelectronics.feature.products.data.repository

import com.elitec.kingelectronics.feature.products.data.mapper.toData
import com.elitec.kingelectronics.feature.products.data.mapper.toDomain
import com.elitec.kingelectronics.feature.products.domain.entity.Product
import com.elitec.kingelectronics.feature.products.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val productService: ProductService
): ProductRepository {
    override suspend fun save(newProduct: Product): Long =
        productService.create(newProduct.toData())

    override suspend fun modify(
        productId: Long,
        modifiedProduct: Product,
    ) {
        productService.update(productId, modifiedProduct.toData())
    }

    override suspend fun getProductById(productId: Long): Product? =
        productService.read(productId)?.toDomain()

    override suspend fun getAllProducts(): List<Product> =
        productService.readAll().map { it.toDomain() }

    override suspend fun getAllProductWithPaging(index: Int): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProduct(productId: Long) {
        productService.delete(productId)
    }
}