package com.elitec.kingelectronics.feature.categories.data.repository

import com.elitec.kingelectronics.feature.categories.data.dto.CategoryDto
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.deleteWhere
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.jdbc.update
import org.koin.core.qualifier.named
import kotlin.collections.get
import kotlin.text.category
import kotlin.text.set

class CategoryService(
    private val db: Database
) {
    object Category: Table() {
        val id = long("id").autoIncrement()
        val name = varchar("name", length = 50)
        val iconUrl = varchar("icon_url", length = 255)

        override val primaryKey = PrimaryKey(id)
    }

    init {
        transaction(db) {
            SchemaUtils.create(Category)
        }
    }

    suspend fun create(category: CategoryDto): Long = dbQuery {
        Category.insert {
            it[name] = category.name
            it[iconUrl] = category.iconUrl ?: ""
        } [Category.id]
    }

    suspend fun read(id: Long): CategoryDto? = dbQuery {
        Category.selectAll()
            .where { Category.id eq id }
            .map {
                CategoryDto(
                    id = it[Category.id],
                    name = it[Category.name],
                    iconUrl = it[Category.iconUrl]
                )
            }
            .singleOrNull()
    }

    suspend fun readAll(): List<CategoryDto> = dbQuery {
        Category.selectAll()
            .map {
                CategoryDto(
                    id = it[Category.id],
                    name = it[Category.name],
                    iconUrl = it[Category.iconUrl]
                )
            }
    }

    suspend fun update(id: Long, category: CategoryDto) {
        dbQuery {
            Category.update(
                { Category.id eq id }
            ) {
                it[name] = category.name
                it[iconUrl] = category.iconUrl ?: ""
            }
        }
    }

    suspend fun delete(id: Long) {
        dbQuery {
            Category.deleteWhere { Category.id.eq(id) }
        }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        suspendTransaction(db) { block() }
}