package com.elitec.kingelectronics.feature.sale.data.repository

import com.elitec.kingelectronics.feature.sale.data.dto.SaleDto
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

class SalesDataSource(
    private val db: Database
) {
    init {
        transaction(db) {
            SchemaUtils.create(SaleTable)
        }
    }

    suspend fun create(sale: SaleDto): Long = dbQuery {
        SaleTable.insert {
            it[date] = date
            it[amount] = amount
            it[verified] = verified
            it[deliveryType] = deliveryType
            it[deliveryAddress] = deliveryAddress
            it[userId] = userId
            it[customerName] = customerName
            it[products] = products
        } [SaleTable.id]
    }

    suspend fun read(id: Long): SaleDto? = dbQuery {
        SaleTable
            .select(SaleTable.id eq id)
            .map {
                SaleDto(
                    id = it[SaleTable.id],
                    date = it[SaleTable.date],
                    amount = it[SaleTable.amount],
                    verified = it[SaleTable.verified],
                    products = it[SaleTable.products],
                    userId = it[SaleTable.userId],
                    customerName = it[SaleTable.customerName],
                    deliveryType = it[SaleTable.deliveryType],
                    deliveryAddress = it[SaleTable.deliveryAddress]
                )
            }
            .singleOrNull()
    }

    suspend fun readAll(): List<SaleDto> = dbQuery {
        SaleTable.selectAll()
            .map {
                SaleDto(
                    id = it[SaleTable.id],
                    date = it[SaleTable.date],
                    amount = it[SaleTable.amount],
                    verified = it[SaleTable.verified],
                    products = it[SaleTable.products],
                    userId = it[SaleTable.userId],
                    customerName = it[SaleTable.customerName],
                    deliveryType = it[SaleTable.deliveryType],
                    deliveryAddress = it[SaleTable.deliveryAddress]
                )
            }
    }

    suspend fun getAll(limit: Int, offset: Long): List<SaleDto> = dbQuery {
        SaleTable
            .selectAll()
            .limit(limit)
            .offset(offset)
            .map {
                SaleDto(
                    id = it[SaleTable.id],
                    date = it[SaleTable.date],
                    amount = it[SaleTable.amount],
                    verified = it[SaleTable.verified],
                    products = it[SaleTable.products],
                    userId = it[SaleTable.userId],
                    customerName = it[SaleTable.customerName],
                    deliveryType = it[SaleTable.deliveryType],
                    deliveryAddress = it[SaleTable.deliveryAddress]
                )
            }
    }

    suspend fun update(id: Long, sale: SaleDto) {
        dbQuery {
            val updatedRow = SaleTable.update(
                { SaleTable.id eq id }
            ) {
                it[date] = date
                it[amount] = amount
                it[verified] = verified
                it[deliveryType] = deliveryType
                it[deliveryAddress] = deliveryAddress
                it[userId] = userId
                it[customerName] = customerName
                it[products] = products
            }

            if (updatedRow == 0) {
                throw NoSuchElementException("Sale with id $id not found")
            }
        }
    }

    suspend fun delete(id: Long) {
        dbQuery {
            val deletedRow = SaleTable.deleteWhere { SaleTable.id.eq(id) }

            if (deletedRow == 0) {
                throw NoSuchElementException("Sale with id $id not found")
            }
        }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        suspendTransaction(db) { block() }
}