package com.elitec.kingelectronics.feature.categories.data.repository

import com.elitec.kingelectronics.feature.categories.data.dto.CategoryDto
import io.ktor.server.plugins.NotFoundException
import org.jetbrains.exposed.v1.core.Expression
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

class CategoryService(
    private val db: Database
) {
    init {
        transaction(db) {
            SchemaUtils.create(CategoryTable)
        }
    }

    suspend fun create(category: CategoryDto): Long = dbQuery {
        CategoryTable.insert {
            it[name] = category.name
            it[iconUrl] = category.iconUrl ?: ""
        } [CategoryTable.id]
    }

    suspend fun read(id: Long): CategoryDto? = dbQuery {
        CategoryTable
            .select(CategoryTable.id eq id)
            .map {
                CategoryDto(
                    id = it[CategoryTable.id],
                    name = it[CategoryTable.name],
                    iconUrl = it[CategoryTable.iconUrl]
                )
            }
            .singleOrNull()
    }

    suspend fun readAll(): List<CategoryDto> = dbQuery {
        CategoryTable.selectAll()
            .map {
                CategoryDto(
                    id = it[CategoryTable.id],
                    name = it[CategoryTable.name],
                    iconUrl = it[CategoryTable.iconUrl]
                )
            }
    }

    suspend fun update(id: Long, category: CategoryDto) {
        dbQuery {
            val updatedRows = CategoryTable.update(
                { CategoryTable.id eq id }
            ) {
                it[name] = category.name
                it[iconUrl] = category.iconUrl ?: ""
            }

            if (updatedRows == 0) {
                throw NoSuchElementException("Category with id $id not found")
            }
        }
    }

    suspend fun delete(id: Long) {
        dbQuery {
            val deletedRows = CategoryTable.deleteWhere { CategoryTable.id eq id }

            if (deletedRows == 0) {
                throw NotFoundException("Category with id $id not found")
            }
        }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        suspendTransaction(db) { block() }
}