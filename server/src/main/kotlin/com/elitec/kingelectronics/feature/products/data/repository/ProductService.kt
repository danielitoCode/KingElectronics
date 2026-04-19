package com.elitec.kingelectronics.feature.products.data.repository

import com.elitec.kingelectronics.feature.categories.data.repository.CategoryService.Category
import com.elitec.kingelectronics.feature.products.data.dto.ProductDto
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.deleteWhere
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.jdbc.update

class ProductService(
    private val db: Database
) {
    object Product: Table() {
        val id = long("id").autoIncrement()
        val name = varchar("name",100)
        val description = varchar("description", 255)
        val price = double("price")
        val photoUrl = varchar("photo_url", 255)
        val categoryId = long("category_id")
        val rating = double("rating")
    }

    init {
        transaction(db) {
            SchemaUtils.create(Product)
        }
    }

    suspend fun create(product: ProductDto): Long = dbQuery {
        Product.insert {
            it[name] = product.name
            it[description] = product.description
            it[photoUrl] = product.photoUrl
            it[price] = product.price
            it[categoryId] = product.categoryId
            it[rating] = product.rating
        } [Category.id]
    }

    suspend fun read(id: Long): ProductDto? = dbQuery {
        Product.selectAll()
            .where { Product.id eq id }
            .map {
                ProductDto(
                    id = it[Product.id],
                    name = it[Product.name],
                    price = it[Product.price],
                    description = it[Product.description],
                    photoUrl = it[Product.photoUrl],
                    rating = it[Product.rating],
                    categoryId = it[Product.categoryId]
                )
            }
            .singleOrNull()
    }

    suspend fun readAll(): List<ProductDto> = dbQuery {
        Product.selectAll()
            .map {
                ProductDto(
                    id = it[Category.id],
                    name = it[Product.name],
                    price = it[Product.price],
                    description = it[Product.description],
                    photoUrl = it[Product.photoUrl],
                    rating = it[Product.rating],
                    categoryId = it[Product.categoryId]
                )
            }
    }

    suspend fun update(id: Long, product: ProductDto) {
        dbQuery {
            Product.update(
                { Product.id eq id }
            ) {
                it[name] = product.name
                it[description] = product.description
                it[photoUrl] = product.photoUrl
                it[price] = product.price
                it[categoryId] = product.categoryId
                it[rating] = product.rating
            }
        }
    }

    suspend fun delete(id: Long) {
        dbQuery {
            Product.deleteWhere { Product.id.eq(id) }
        }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        suspendTransaction(db) { block() }
}