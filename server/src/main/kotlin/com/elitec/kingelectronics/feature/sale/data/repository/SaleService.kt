package com.elitec.kingelectronics.feature.sale.data.repository

import com.elitec.kingelectronics.feature.sale.data.dto.SaleDto
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

class SaleService(
    private val db: Database
) {
    object Sale: Table() {
        val id = long("id").autoIncrement()
        val date = varchar("date", 100)
        val amount = double("amount")
        val verified = varchar("verified", 20)
        val products = varchar("products", 1028)
        val userId = long("user_id")
        val customerName = varchar("customer_name", 255)
        val deliveryType = varchar("delivery_type", 15)
        val deliveryAddress = varchar("delivery_address", 510)
    }

    init {
        transaction(db) {
            SchemaUtils.create(Sale)
        }
    }

    suspend fun create(sale: SaleDto): Long = dbQuery {
        Sale.insert {
            it[date] = date
            it[amount] = amount
            it[verified] = verified
            it[deliveryType] = deliveryType
            it[deliveryAddress] = deliveryAddress
            it[userId] = userId
            it[customerName] = customerName
            it[products] = products
        } [Sale.id]
    }

    suspend fun read(id: Long): SaleDto? = dbQuery {
        Sale.selectAll()
            .where { Sale.id eq id }
            .map {
                SaleDto(
                    id = it[Sale.id],
                    date = it[Sale.date],
                    amount = it[Sale.amount],
                    verified = it[Sale.verified],
                    products = it[Sale.products],
                    userId = it[Sale.userId],
                    customerName = it[Sale.customerName],
                    deliveryType = it[Sale.deliveryType],
                    deliveryAddress = it[Sale.deliveryAddress]
                )
            }
            .singleOrNull()
    }

    suspend fun readAll(): List<SaleDto> = dbQuery {
        Sale.selectAll()
            .map {
                SaleDto(
                    id = it[Sale.id],
                    date = it[Sale.date],
                    amount = it[Sale.amount],
                    verified = it[Sale.verified],
                    products = it[Sale.products],
                    userId = it[Sale.userId],
                    customerName = it[Sale.customerName],
                    deliveryType = it[Sale.deliveryType],
                    deliveryAddress = it[Sale.deliveryAddress]
                )
            }
    }

    suspend fun update(id: Long, sale: SaleDto) {
        dbQuery {
            Sale.update(
                { Sale.id eq id }
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
        }
    }

    suspend fun delete(id: Long) {
        dbQuery {
            Sale.deleteWhere { Sale.id.eq(id) }
        }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        suspendTransaction(db) { block() }
}