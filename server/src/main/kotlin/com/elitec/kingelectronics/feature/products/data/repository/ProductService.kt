package com.elitec.kingelectronics.feature.products.data.repository

import com.elitec.kingelectronics.feature.products.data.dto.ProductDto
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.deleteWhere
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.jdbc.update

class ProductService(
    private val db: Database
) {
    init {
        transaction(db) {
            SchemaUtils.create(ProductTable)
        }
    }

    suspend fun create(product: ProductDto): Long = dbQuery {
        ProductTable.insert {
            it[name] = product.name
            it[description] = product.description
            it[photoUrl] = product.photoUrl
            it[price] = product.price
            it[categoryId] = product.categoryId
            it[rating] = product.rating
        } [ProductTable.id]
    }

    suspend fun read(id: Long): ProductDto? = dbQuery {
        ProductTable
            .select(ProductTable.id eq id )
            .map {
                ProductDto(
                    id = it[ProductTable.id],
                    name = it[ProductTable.name],
                    price = it[ProductTable.price],
                    description = it[ProductTable.description],
                    photoUrl = it[ProductTable.photoUrl],
                    rating = it[ProductTable.rating],
                    categoryId = it[ProductTable.categoryId]
                )
            }
            .singleOrNull()
    }

    suspend fun readAll(): List<ProductDto> = dbQuery {
        ProductTable.selectAll()
            .map {
                ProductDto(
                    id = it[ProductTable.id],
                    name = it[ProductTable.name],
                    price = it[ProductTable.price],
                    description = it[ProductTable.description],
                    photoUrl = it[ProductTable.photoUrl],
                    rating = it[ProductTable.rating],
                    categoryId = it[ProductTable.categoryId]
                )
            }
    }

    suspend fun update(id: Long, product: ProductDto) {
        dbQuery {
            val updateRow = ProductTable.update(
                { ProductTable.id eq id }
            ) {
                it[name] = product.name
                it[description] = product.description
                it[photoUrl] = product.photoUrl
                it[price] = product.price
                it[categoryId] = product.categoryId
                it[rating] = product.rating
            }

            if (updateRow == 0) {
                throw NoSuchElementException("Product with id $id not found")
            }
        }
    }

    suspend fun delete(id: Long) {
        dbQuery {
            val deletedRow = ProductTable.deleteWhere { ProductTable.id.eq(id) }

            if (deletedRow == 0) {
                throw NoSuchElementException("Product with id $id not found")
            }
        }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        suspendTransaction(db) { block() }
}