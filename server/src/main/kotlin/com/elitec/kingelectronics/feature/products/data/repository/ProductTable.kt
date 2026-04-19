package com.elitec.kingelectronics.feature.products.data.repository

import org.jetbrains.exposed.v1.core.Table

object ProductTable : Table() {
    val id = long("id").autoIncrement()
    val name = varchar("name",100)
    val description = varchar("description", 255)
    val price = double("price")
    val photoUrl = varchar("photo_url", 255)
    val categoryId = long("category_id")
    val rating = double("rating")
}