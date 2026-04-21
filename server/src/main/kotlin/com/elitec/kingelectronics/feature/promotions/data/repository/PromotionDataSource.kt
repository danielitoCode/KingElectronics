package com.elitec.kingelectronics.feature.promotions.data.repository

import com.elitec.kingelectronics.feature.account.data.dto.UserDto
import com.elitec.kingelectronics.feature.accounts.data.repository.AccountsTable
import com.elitec.kingelectronics.feature.promotions.data.dto.PromotionDto
import io.ktor.server.plugins.NotFoundException
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

class PromotionDataSource(
    private val db: Database
) {
    init {
        transaction(db) {
            SchemaUtils.create(PromotionTable)
        }
    }

    suspend fun create(promotionDto: PromotionDto): Long = dbQuery {
        PromotionTable.insert {
            it[title] = promotionDto.title
            it[description] = promotionDto.description
            it[oldPrice] = promotionDto.oldPrice ?: 0.0
            it[newPrice] =  promotionDto.newPrice ?: 0.0
            it[photoUrl] = promotionDto.photoUrl ?: ""
        } [PromotionTable.id]
    }

    suspend fun read(id: Long): PromotionDto? = dbQuery {
        PromotionTable
            .select(PromotionTable.id eq id)
            .map {
                PromotionDto(
                    id = it[PromotionTable.id],
                    title = it[PromotionTable.title],
                    description = it[PromotionTable.description],
                    oldPrice = it[PromotionTable.oldPrice],
                    newPrice = it[PromotionTable.newPrice],
                    photoUrl = it[PromotionTable.photoUrl]
                )
            }
            .singleOrNull()
    }

    suspend fun getAll(limit: Int, offset: Long): List<PromotionDto> = dbQuery {
        PromotionTable
            .selectAll()
            .limit(limit)
            .offset(offset)
            .map {
                PromotionDto(
                    id = it[PromotionTable.id],
                    title = it[PromotionTable.title],
                    description = it[PromotionTable.description],
                    oldPrice = it[PromotionTable.oldPrice],
                    newPrice = it[PromotionTable.newPrice],
                    photoUrl = it[PromotionTable.photoUrl]
                )
            }
    }

    suspend fun readAll(): List<PromotionDto> = dbQuery {
        PromotionTable.selectAll()
            .map {
                PromotionDto(
                    id = it[PromotionTable.id],
                    title = it[PromotionTable.title],
                    description = it[PromotionTable.description],
                    oldPrice = it[PromotionTable.oldPrice],
                    newPrice = it[PromotionTable.newPrice],
                    photoUrl = it[PromotionTable.photoUrl]
                )
            }
    }

    suspend fun update(id: Long, promotionDto: PromotionDto) {
        dbQuery {
            val updatedRows = PromotionTable.update(
                { PromotionTable.id eq id }
            ) {
                it[title] = promotionDto.title
                it[description] = promotionDto.description
                it[oldPrice] = promotionDto.oldPrice ?: 0.0
                it[newPrice] =  promotionDto.newPrice ?: 0.0
                it[photoUrl] = promotionDto.photoUrl ?: ""
            }

            if (updatedRows == 0) {
                throw NoSuchElementException("user with id $id not found")
            }
        }
    }

    suspend fun delete(id: Long) {
        dbQuery {
            val deletedRows = PromotionTable.deleteWhere { PromotionTable.id eq id }

            if (deletedRows == 0) {
                throw NotFoundException("Account with id $id not found")
            }
        }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        suspendTransaction(db) { block() }
}