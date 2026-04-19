package com.elitec.kingelectronics.feature.sale.data.repository

import org.jetbrains.exposed.v1.core.Table

object SaleTable: Table() {
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